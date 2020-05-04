package com.aims.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.CompositeItemWriter;
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
import com.aims.listener.JobCompletionListener;
import com.aims.mapper.EmployeeRowMapper;
import com.aims.model.HCDetails;
import com.aims.model.HCEmployee;
import com.aims.processor.ProcessHCEmployeeBuild;
import com.aims.processor.ProcessorHCMasterBuild;
import com.aims.tasklet.DbWriteTasklet;

@Configuration
public class BatchConfig {

	
	  @Autowired public JobBuilderFactory jobBuilderFactory;
	  
	  @Autowired public StepBuilderFactory stepBuilderFactory;

	  
	    @Autowired
	    private EntityManagerFactory emf;
	    
	    @Autowired
	    private DataSource datasource;
	
	 @Bean public Job processHeadCountJob() throws Exception { return jobBuilderFactory.get("processHeadCountJob")
	 .incrementer(new RunIdIncrementer()).listener(listener())
	 .start(performHCVersionSave()).on("*").to(orderStep2()).end().build(); } 
	 
	 @Bean("hcversionsave")
	 public Step performHCVersionSave() { return
			 stepBuilderFactory.get("performVersionSave").tasklet(new DbWriteTasklet(datasource)).listener(promotionListener()).build(); } 
	 
	 @Bean("masterload")
	 @DependsOn("hcversionsave")
	 public Step orderStep2() throws Exception { return
	 stepBuilderFactory.get("orderStep2").<Employee, HCDetails> chunk(1000) .reader(excelStudentReader())
	 .processor(processor())
	 .writer(writer())
	 .build(); }
	 
	/* @Bean
	 @DependsOn("masterload")
	 public Step orderStep3() { return
			 stepBuilderFactory.get("orderStep3").<HCDetails,Object> chunk(1000).reader(databaseItemReader(0))
			 .processor(compositeItemProcessor()).writer(compositeItemWriter()).build(); }*/
	 
	 @Bean
	 public ExecutionContextPromotionListener promotionListener() {
	 	ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();

	 	listener.setKeys(new String[] {"versionNo" });

	 	return listener;
	 }
	 
		 
	/* @Bean public Step orderStep1() { return
	 stepBuilderFactory.get("orderStep1").<HCIntermediate, String>
	 chunk(1).reader(databaseItemReader()).processor(processor2()) .build(); }*/
		
	 
	 @Bean public JobExecutionListener listener() { return new
			 JobCompletionListener(); }
	 

	 @Lazy
	 @Bean	 
    ItemReader<Employee> excelStudentReader() throws Exception {		 	
		 
	        PoiItemReader<Employee> reader = new PoiItemReader<Employee>();
	        PushbackInputStream input = null;
	       
	        
	        
			JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);		
			
			input = new PushbackInputStream(new ByteArrayInputStream(ConfigurationHelper.getHcInter(jdbcTemplate)));
			
	        InputStreamResource resource = new InputStreamResource(input);
	        reader.setUseDataFormatter(true);
	        reader.setLinesToSkip(1);
	        reader.setResource(resource);
	        reader.setRowMapper(excelRowMapper(jdbcTemplate));
	        reader.setCurrentSheet(1);
	       
	        
	        
	        
	        return reader;
	    }
	
	 @Bean
	 @StepScope
	 JdbcCursorItemReader<HCDetails> databaseItemReader(@Value("#{jobExecutionContext[versionNo]}")int version) { 
		 Map<String, Object> namedParameters = new HashMap<String, Object>() {{
				put("versionNum", version);
			}};
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
	    
	    @Lazy
	    @Bean	   
	    public ItemProcessor<Employee, HCDetails> processor() {
	        return new ProcessorHCMasterBuild();

	    }
	    
	    @Lazy
	    @Bean
	    public CompositeItemProcessor<HCDetails, ?> compositeItemProcessor(){
	    	List<ItemProcessor<HCDetails, ?>> delegates = new ArrayList<>();
	    	delegates.add(new ProcessHCEmployeeBuild());
	        CompositeItemProcessor<HCDetails, ?> processor = new CompositeItemProcessor<>();
	        processor.setDelegates(delegates);
	        return processor;
	    	
	    }
	    
	   /* @Bean
	    public ItemProcessor<HCIntermediate, String> processor2() {
	        return new ProcessSetFileResource();
	    }*/

	    
	    @Lazy
	    @Bean	    
	    public JpaItemWriter<HCDetails> writer() {
	        JpaItemWriter<HCDetails> writer = new JpaItemWriter<>();
	        writer.setEntityManagerFactory(emf);
	        return writer;
	    }
	    
	    
	    @Lazy
	    @Bean	    
	    public JpaItemWriter<HCEmployee> writer2() {
	        JpaItemWriter<HCEmployee> writer = new JpaItemWriter<>();
	        writer.setEntityManagerFactory(emf);
	        return writer;
	    }
	    
	    public CompositeItemWriter<?> compositeItemWriter(){
	        CompositeItemWriter writer = new CompositeItemWriter();
	        writer.setDelegates(Arrays.asList(writer2()));
	        return writer;
	    }

	    
	   
	    
		/*
		 * @Bean public JpaItemWriter<VersionInfo> writer2() {
		 * JpaItemWriter<VersionInfo> writer = new JpaItemWriter<>();
		 * writer.setEntityManagerFactory(emf); return writer; }
		 */

}
