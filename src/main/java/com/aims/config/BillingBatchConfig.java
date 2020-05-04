package com.aims.config;

import java.io.ByteArrayInputStream;
import java.io.PushbackInputStream;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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
import org.springframework.jdbc.core.JdbcTemplate;

import com.aims.bo.BillingDetails;
import com.aims.helper.ConfigurationHelper;
import com.aims.listener.JobCompletionListener;
import com.aims.mapper.BillingMasterRowMapper;
import com.aims.mapper.BillingRowMapper;
import com.aims.model.BillingMaster;
import com.aims.model.BillingVersion;
import com.aims.processor.ProcessBillingMasterBuild;
import com.aims.processor.ProcessBillingVersionBuild;

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

	@Bean
	public Job processBillingDetailsJob() throws Exception {
		return jobBuilderFactory.get("processBillingDetailsJob").incrementer(new RunIdIncrementer())
				.listener(billingListener()).flow(performBillingVersionSave()).next(performBillingMasterSave()).end()
				.build();
	}

	@Bean
	public JobExecutionListener billingListener() {
		return new JobCompletionListener();
	}

	@Bean("billingversionsave")
	public Step performBillingVersionSave() throws Exception {
		return stepBuilderFactory.get("performBillingVersionSave").<BillingDetails, BillingVersion>chunk(1000)
				.reader(billingReader()).processor(billVersionprocessor()).build();
	}

	@Bean("billingmastersave")
	@DependsOn("billingversionsave")
	public Step performBillingMasterSave() throws Exception {
		return stepBuilderFactory.get("performBillingMasterSave").<BillingDetails, BillingMaster>chunk(1000)
				.reader(billingMasterReader()).processor(billMasterProcessor()).writer(billMasterWriter()).build();
	}

	@Lazy
	@Bean
	ItemReader<BillingDetails> billingReader() throws Exception {

		PoiItemReader<BillingDetails> reader = new PoiItemReader<BillingDetails>();
		PushbackInputStream input = null;
		try {

			JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);

			input = new PushbackInputStream(new ByteArrayInputStream(ConfigurationHelper.getHcInter(jdbcTemplate)));

			InputStreamResource resource = new InputStreamResource(input);
			reader.setUseDataFormatter(true);
			reader.setLinesToSkip(2);
			reader.setResource(resource);
			reader.setCurrentSheet(0);
			reader.setRowMapper(billingRowMapper());

		} catch (Exception ex) {
			System.out.println("Upload file is of not of type head count report");
		}

		return reader;
	}

	@Lazy
	@Bean	
	ItemReader<BillingDetails> billingMasterReader() throws Exception {

		PoiItemReader<BillingDetails> reader = new PoiItemReader<BillingDetails>();
		PushbackInputStream input = null;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);

		input = new PushbackInputStream(new ByteArrayInputStream(ConfigurationHelper.getHcInter(jdbcTemplate)));

		InputStreamResource resource = new InputStreamResource(input);
		reader.setUseDataFormatter(true);
		reader.setLinesToSkip(2);
		reader.setResource(resource);
		reader.setCurrentSheet(0);
		reader.setRowMapper(billingMasterRowMapper());

		return reader;
	}

	private RowMapper<BillingDetails> billingRowMapper() {

		return new BillingRowMapper();
	}

	private RowMapper<BillingDetails> billingMasterRowMapper() {

		return new BillingMasterRowMapper();
	}

	@Lazy
	@Bean
	public ItemProcessor<BillingDetails, BillingVersion> billVersionprocessor() {
		return new ProcessBillingVersionBuild(datasource);

	}
	
	@Lazy
	@Bean
	@StepScope
	public ItemProcessor<BillingDetails, BillingMaster> billMasterProcessor() {
		return new ProcessBillingMasterBuild(datasource);

	}

	/*@Lazy
	@Bean
	public JpaItemWriter<BillingVersion> billVersionWriter() {
		JpaItemWriter<BillingVersion> writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(emf);
		return writer;
	}*/
	
	@Lazy
	@Bean	
	public JpaItemWriter<BillingMaster> billMasterWriter() {
		JpaItemWriter<BillingMaster> writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(emf);
		return writer;
	}
}
