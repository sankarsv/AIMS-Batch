package com.aims.config;

import java.io.ByteArrayInputStream;
import java.io.PushbackInputStream;

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
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.InputStreamResource;

import com.aims.bo.ClarityDetails;
import com.aims.dao.BatchDao;
import com.aims.listener.ClarityJobCompletionListener;
import com.aims.mapper.ClarityRowMapper;
import com.aims.model.ClarityMaster;
import com.aims.processor.ClarityProcessor;
import com.aims.step.ClarityWriter;
import com.aims.tasklet.ClarityVersionUpdateTasklet;
import com.aims.tasklet.ErrorTasklet;
import com.aims.tasklet.SuccessTasklet;

@Configuration
public class ClarityBatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource datasource;	
	
	private BatchDao dao;

	@Bean
	public Job clarityJob() throws Exception {
		return jobBuilderFactory.get("clarityJob").incrementer(new RunIdIncrementer()).listener(clarityListener())
				.start(clarityVersionUpdateTasklet()).next(saveClarityDetails()).on(ExitStatus.FAILED.getExitCode())
				.to(clarityErrorTasklet()).from(saveClarityDetails()).on("*").to(claritySuccessTasklet()).end().build();
	}

	@Bean
	@Lazy
	public JobExecutionListener clarityListener() {
		return new ClarityJobCompletionListener(getDao());
	}

	@Bean("clarityErrorTasklet")
	public Step clarityErrorTasklet() {
		return stepBuilderFactory.get("clarityErrorTasklet").tasklet(new ErrorTasklet()).build();
	}

	@Bean("clarityVersionUpdateTasklet")
	public Step clarityVersionUpdateTasklet() {
		return stepBuilderFactory.get("clarityVersionUpdateTasklet").tasklet(new ClarityVersionUpdateTasklet(getDao())).build();
	}

	@Bean("saveClarityDetails")
	public Step saveClarityDetails() throws Exception {
		return stepBuilderFactory.get("saveClarityDetails").<ClarityDetails, ClarityMaster>chunk(1000)
				.reader(clarityReader()).processor(clarityProcessor()).writer(new ClarityWriter()).build();
	}

	@Bean("claritySuccessTasklet")
	public Step claritySuccessTasklet() {
		return stepBuilderFactory.get("claritySuccessTasklet").tasklet(new SuccessTasklet()).build();
	}

	@Bean
	@Lazy
	ItemReader<ClarityDetails> clarityReader() throws Exception {

		PoiItemReader<ClarityDetails> reader = new PoiItemReader<ClarityDetails>();
		PushbackInputStream input = null;
		try {
			input = new PushbackInputStream(new ByteArrayInputStream(getDao().getClarityInter()));
		} catch (Exception ex) {
			reader.setRowMapper(clarityRowMapper());
			return reader;

		}
		reader.setRowMapper(clarityRowMapper());
		InputStreamResource resource = new InputStreamResource(input);
		reader.setUseDataFormatter(true);
		reader.setLinesToSkip(1);
		reader.setResource(resource);

		reader.setCurrentSheet(7);

		return reader;
	}

	private RowMapper<ClarityDetails> clarityRowMapper() {

		return new ClarityRowMapper();
	}

	@Bean
	@StepScope
	public ItemProcessor<ClarityDetails, ClarityMaster> clarityProcessor() {
		return new ClarityProcessor(getDao());

	}
	
	private BatchDao getDao() {
		if(dao==null) {
			dao = new BatchDao(datasource);
		}
		return dao;
	}

}
