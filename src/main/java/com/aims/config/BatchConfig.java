package com.aims.config;

import java.io.ByteArrayInputStream;
import java.io.PushbackInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.aims.bo.Employee;
import com.aims.helper.ConfigurationHelper;
import com.aims.helperinterface.BatchToOnlineTriggerhelperInterface;
import com.aims.listener.JobCompletionListener;
import com.aims.mapper.EmployeeRowMapper;
import com.aims.model.HCDetails;
import com.aims.processor.ProcessorHCMasterBuild;
import com.aims.tasklet.DbWriteTasklet;
import com.aims.tasklet.ErrorTasklet;
import com.aims.tasklet.HCChildSaveTasklet;

@Configuration
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private EntityManagerFactory emf;

	@Autowired
	BatchToOnlineTriggerhelperInterface batchtoOnlineInt;

	@Autowired
	private DataSource datasource;

	@Bean
	public Job processHeadCountJob() throws Exception {
		return jobBuilderFactory.get("processHeadCountJob").incrementer(new RunIdIncrementer()).listener(listener())
				.start(performHCVersionSave()).on("*")
				.to(orderStep2())
				.on(ExitStatus.FAILED.getExitCode())
				.to(errorTasklet())
				.from(orderStep2())
				.on("*").to(performHCChildTableSave()).end().build();
	}

	//.next(performHCChildTableSave())
	@Bean("hcversionsave")
	public Step performHCVersionSave() {
		return stepBuilderFactory.get("performVersionSave").tasklet(new DbWriteTasklet(datasource))
				.listener(promotionListener()).build();
	}

	@Bean("hcChildTableSave")
	public Step performHCChildTableSave() {
		return stepBuilderFactory.get("performHCChildTableSave").tasklet(new HCChildSaveTasklet(batchtoOnlineInt))
				.build();
	}
	
	@Bean("errorTasklet")
	public Step errorTasklet() {
		return stepBuilderFactory.get("errorTasklet").tasklet(new ErrorTasklet())
				.build();
	}


	@Bean("masterload")
	@DependsOn("hcversionsave")
	public Step orderStep2() throws Exception {
		return stepBuilderFactory.get("orderStep2").<Employee, HCDetails>chunk(1000).reader(excelStudentReader())
				.processor(processor()).writer(writer()).build();
	}

	/*
	 * @Bean
	 * 
	 * @DependsOn("masterload") public Step orderStep3() { return
	 * stepBuilderFactory.get("orderStep3").<HCDetails,Object>
	 * chunk(1000).reader(databaseItemReader(0))
	 * .processor(compositeItemProcessor()).writer(compositeItemWriter()).build(); }
	 */

	@Bean
	public ExecutionContextPromotionListener promotionListener() {
		ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();

		listener.setKeys(new String[] { "versionNo" });

		return listener;
	}

	/*
	 * @Bean public Step orderStep1() { return
	 * stepBuilderFactory.get("orderStep1").<HCIntermediate, String>
	 * chunk(1).reader(databaseItemReader()).processor(processor2()) .build(); }
	 */

	@Bean
	@Lazy
	public JobExecutionListener listener() {
		return new JobCompletionListener(datasource);
	}

	@Bean
	@Lazy
	ItemReader<Employee> excelStudentReader() throws Exception {

		PoiItemReader<Employee> reader = new PoiItemReader<Employee>();
		PushbackInputStream input = null;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
		try {
		input = new PushbackInputStream(new ByteArrayInputStream(ConfigurationHelper.getHcInter(jdbcTemplate)));
		}catch(Exception ex) {
			reader.setRowMapper(excelRowMapper(jdbcTemplate));
			return reader;

		}
		reader.setRowMapper(excelRowMapper(jdbcTemplate));
		InputStreamResource resource = new InputStreamResource(input);
		reader.setUseDataFormatter(true);
		reader.setLinesToSkip(1);
		reader.setResource(resource);
		
		reader.setCurrentSheet(1);

		return reader;
	}

	@Bean
	@StepScope
	JdbcCursorItemReader<HCDetails> databaseItemReader(@Value("#{jobExecutionContext[versionNo]}") int version) {
		Map<String, Object> namedParameters = new HashMap<String, Object>() {
			{
				put("versionNum", version);
			}
		};
		JdbcCursorItemReader<HCDetails> databaseReader = new JdbcCursorItemReader<>();
		databaseReader.setDataSource(datasource);
		databaseReader.setSql("SELECT * FROM aims.hcmaster where version_no= ?");
		databaseReader.setPreparedStatementSetter(new PreparedStatementSetter() {

			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setInt(1, version);
			}
		});
		databaseReader.setRowMapper(new BeanPropertyRowMapper<>(HCDetails.class));
		return databaseReader;
	}

	private RowMapper<Employee> excelRowMapper(JdbcTemplate jdbcTemplate) {

		return new EmployeeRowMapper(jdbcTemplate);
	}

	@Bean
	@StepScope
	public ItemProcessor<Employee, HCDetails> processor() {
		return new ProcessorHCMasterBuild();

	}

	/*
	 * @Bean public ItemProcessor<HCIntermediate, String> processor2() { return new
	 * ProcessSetFileResource(); }
	 */

	@Bean
	@StepScope
	public JpaItemWriter<HCDetails> writer() {
		JpaItemWriter<HCDetails> writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(emf);
		return writer;
	}

	/*
	 * @Bean public JpaItemWriter<VersionInfo> writer2() {
	 * JpaItemWriter<VersionInfo> writer = new JpaItemWriter<>();
	 * writer.setEntityManagerFactory(emf); return writer; }
	 */

}