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
import org.springframework.core.annotation.Order;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aims.bo.Employee;
import com.aims.helper.ConfigurationHelper;
import com.aims.listener.JobCompletionListener;
import com.aims.mapper.EmployeeRowMapper;
import com.aims.model.HCDetails;
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
	    
	    
	   
	 
	
	 @Bean public Job processJob() { return jobBuilderFactory.get("processJob")
	 .incrementer(new RunIdIncrementer()).listener(listener())
	 .start(performVersionSave()).on("*").to(orderStep2()).end().build(); } 
	 
	 @Bean("versionsave")
	 public Step performVersionSave() { return
			 stepBuilderFactory.get("performVersionSave").tasklet(new DbWriteTasklet(datasource)).build(); }
	 
	 
	 
	 @Bean
	 @DependsOn("versionsave")
	 public Step orderStep2() { return
	 stepBuilderFactory.get("orderStep2").<Employee, HCDetails> chunk(1000) .reader(excelStudentReader())
	 .processor(processor())
	 .writer(writer())
	 .build(); }
	 
	
	 
	/* @Bean
	 public ExecutionContextPromotionListener promotionListener() {
	 	ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();

	 	listener.setKeys(new String[] {"versionNo" });

	 	return listener;
	 }*/
	 
	/* @Bean public Step orderStep1() { return
	 stepBuilderFactory.get("orderStep1").<HCIntermediate, String>
	 chunk(1).reader(databaseItemReader()).processor(processor2()) .build(); }*/
		
	 
	 @Bean public JobExecutionListener listener() { return new
			 JobCompletionListener(); }
	 

	 @Lazy
	 @Bean	 
    ItemReader<Employee> excelStudentReader() {		 	
		 
	        PoiItemReader<Employee> reader = new PoiItemReader<Employee>();
	        System.out.println("Entered configuration");
	        PushbackInputStream input = null;
	        
			JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);		
			
			input = new PushbackInputStream(new ByteArrayInputStream(ConfigurationHelper.getHcInter(jdbcTemplate)));
			
	        InputStreamResource resource = new InputStreamResource(input);
	        reader.setUseDataFormatter(true);
	        reader.setLinesToSkip(1);
	        reader.setResource(resource);
	        reader.setCurrentSheet(1);
	       
	        reader.setRowMapper(excelRowMapper(jdbcTemplate));
	        return reader;
	    }
	
	/* @Bean
	    ItemReader<HCIntermediate> databaseItemReader() {  
		 	JdbcCursorItemReader<HCIntermediate> databaseReader = new JdbcCursorItemReader<>();
		 	databaseReader.setDataSource(datasource);
		 	databaseReader.setSql(sql);
		 	databaseReader.setRowMapper(new BeanPropertyRowMapper<>(HCIntermediate.class));
	        return databaseReader;
	    }*/
	
	    private RowMapper<Employee> excelRowMapper(JdbcTemplate jdbcTemplate) {
	    	 
	        return new EmployeeRowMapper(jdbcTemplate);
	    }
	    
	    @Lazy
	    @Bean	   
	    public ItemProcessor<Employee, HCDetails> processor() {
	        return new ProcessorHCMasterBuild();
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
	    
		/*
		 * @Bean public JpaItemWriter<VersionInfo> writer2() {
		 * JpaItemWriter<VersionInfo> writer = new JpaItemWriter<>();
		 * writer.setEntityManagerFactory(emf); return writer; }
		 */

}
