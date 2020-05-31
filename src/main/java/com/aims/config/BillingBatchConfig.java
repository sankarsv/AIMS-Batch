package com.aims.config;

import java.io.ByteArrayInputStream;
import java.io.PushbackInputStream;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.InputStreamResource;

import com.aims.bo.BillingDetails;
import com.aims.dao.BatchDao;
import com.aims.listener.BillingJobCompletionListener;
import com.aims.mapper.BillingMasterRowMapper;
import com.aims.mapper.BillingRowMapper;
import com.aims.model.BillingMaster;
import com.aims.model.BillingVersion;
import com.aims.processor.ProcessBillingMasterBuild;
import com.aims.processor.ProcessBillingVersionBuild;
import com.aims.step.BillingMasterWriter;
import com.aims.step.Writer;
import com.aims.tasklet.ErrorTasklet;

@Configuration
public class BillingBatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private EntityManagerFactory emf;
	
	@Autowired
	private DataSource datasource;	
	
	private BatchDao dao;

	@Bean
	public Job processBillingDetailsJob() throws Exception {
		return jobBuilderFactory.get("processBillingDetailsJob").incrementer(new RunIdIncrementer())
				.listener(billingListener())
				.start(performBillingVersionSave())
				.on(ExitStatus.FAILED.getExitCode()).to(billingErrorTasklet())
				.from(performBillingVersionSave())
				.on(("*")).to(performBillingMasterSave())
				.on(ExitStatus.FAILED.getExitCode()).to(billingErrorTasklet()).end()
				.build();
	}

	@Bean
	@Lazy
	public JobExecutionListener billingListener() {
		return new BillingJobCompletionListener(getDao() );
	}
	
	@Bean("billingErrorTasklet")
	public Step billingErrorTasklet() {
		return stepBuilderFactory.get("billingErrorTasklet").tasklet(new ErrorTasklet())
				.build();
	}

	@Bean("billingversionsave")
	public Step performBillingVersionSave() throws Exception {
		return stepBuilderFactory.get("performBillingVersionSave").<BillingDetails, BillingVersion>chunk(1000)
				.reader(billingReader()).processor(billVersionprocessor()).writer(new Writer()).build();
	}

	@Bean("billingmastersave")
	@DependsOn("billingversionsave")
	public Step performBillingMasterSave() throws Exception {
		return stepBuilderFactory.get("performBillingMasterSave").<BillingDetails, BillingMaster>chunk(1000)
				.reader(billingMasterReader()).processor(billMasterProcessor()).writer(new BillingMasterWriter()).build();
	}

	/*
	 * @Bean("billratesave")
	 * 
	 * @DependsOn("billingmastersave") public Step performBillRateSave() throws
	 * Exception { return
	 * stepBuilderFactory.get("performBillRateSave").<BillingMaster,
	 * BillRate>chunk(100)
	 * .reader(billRateReader()).processor(billRateProcessor()).build(); }
	 */

	
	@Bean
	@Lazy
	ItemReader<BillingDetails> billingReader() throws Exception {

		PoiItemReader<BillingDetails> reader = new PoiItemReader<BillingDetails>();
		PushbackInputStream input = null;
		InputStreamResource resource = null;
		try {
			reader.setRowMapper(billingRowMapper());
			input = new PushbackInputStream(new ByteArrayInputStream(getDao().getBrInter()));
			resource = new InputStreamResource(input);
		} catch (Exception ex) {
			System.out.println("There was a problem during upload of BillingDetails");
		}

			
			reader.setUseDataFormatter(true);
			reader.setLinesToSkip(2);
			reader.setResource(resource);
			reader.setCurrentSheet(0);

		

		return reader;
	}

	
	@Bean
	@Lazy
	ItemReader<BillingDetails> billingMasterReader() throws Exception {

		PoiItemReader<BillingDetails> reader = new PoiItemReader<BillingDetails>();
		reader.setRowMapper(billingMasterRowMapper());
		PushbackInputStream input = null;
		InputStreamResource resource = null;
		
		try {
		input = new PushbackInputStream(new ByteArrayInputStream(getDao().getBrInter()));
		resource = new InputStreamResource(input);
		}catch (Exception ex) {
			System.out.println("There was a problem during upload of BillingDetails");
		}
		
		reader.setUseDataFormatter(true);
		reader.setLinesToSkip(2);
		reader.setResource(resource);
		reader.setCurrentSheet(0);
		

		return reader;
	}

	private RowMapper<BillingDetails> billingRowMapper() {

		return new BillingRowMapper();
	}

	private RowMapper<BillingDetails> billingMasterRowMapper() {

		return new BillingMasterRowMapper();
	}

	@Bean
	@StepScope
	public ItemProcessor<BillingDetails, BillingVersion> billVersionprocessor() {
		return new ProcessBillingVersionBuild(getDao());

	}

	@Bean
	@StepScope
	public ItemProcessor<BillingDetails, BillingMaster> billMasterProcessor() {
		return new ProcessBillingMasterBuild(getDao());

	}

	@Bean
	@StepScope
	public JpaItemWriter<BillingMaster> billMasterWriter() {
		JpaItemWriter<BillingMaster> writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(emf);
		return writer;
	}
	
	private BatchDao getDao() {
		if(dao==null) {
			dao = new BatchDao(datasource);
		}
		return dao;
	}

}