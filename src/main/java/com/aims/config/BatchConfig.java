package com.aims.config;

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
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.aims.bo.Employee;
import com.aims.listener.JobCompletionListener;
import com.aims.mapper.EmployeeRowMapper;
import com.aims.model.HCDetails;
import com.aims.model.HCIntermediate;
import com.aims.model.HCVersion;
import com.aims.processor.ProcessSetFileResource;
import com.aims.processor.ProcessorHCMasterBuild;

@Configuration
public class BatchConfig {

	
	  @Autowired public JobBuilderFactory jobBuilderFactory;
	  
	  @Autowired public StepBuilderFactory stepBuilderFactory;

	  
	    @Autowired
	    private EntityManagerFactory emf;
	    
	    @Autowired
	    private DataSource datasource;
	    
	    
	    private String sql = "select MAX(upload_time) as upload_time,filedata from aims.hc_intermediate group by filedata";
	 
	
	 @Bean public Job processJob() { return jobBuilderFactory.get("processJob")
	 .incrementer(new RunIdIncrementer()).listener(listener())
	 .flow(orderStep1()).next(orderStep2()).end().build(); } 
	 
	 @Bean public Step orderStep2() { return
	 stepBuilderFactory.get("orderStep2").<Employee, HCDetails> chunk(1000) .reader(excelStudentReader())
	 .processor(processor())
	 .writer(writer())
	 .build(); }
	 
		
	 @Bean public Step orderStep1() { return
	 stepBuilderFactory.get("orderStep1").<HCIntermediate, String>
	 chunk(1).reader(databaseItemReader()).processor(processor2()) .build(); }
		
	 
	 @Bean public JobExecutionListener listener() { return new
			 JobCompletionListener(); }
	 @Bean
	    ItemReader<Employee> excelStudentReader() {
	        PoiItemReader<Employee> reader = new PoiItemReader<Employee>();
	        System.out.println("Entered configuration");
	        reader.setUseDataFormatter(true);
	        reader.setLinesToSkip(1);
	        reader.setResource(new ClassPathResource("Master_Feed.xlsx"));
	        reader.setCurrentSheet(1);
	        reader.setRowMapper(excelRowMapper());
	        return reader;
	    }
	
	 @Bean
	    ItemReader<HCIntermediate> databaseItemReader() {  
		 	JdbcCursorItemReader<HCIntermediate> databaseReader = new JdbcCursorItemReader<>();
		 	databaseReader.setDataSource(datasource);
		 	databaseReader.setSql(sql);
		 	databaseReader.setRowMapper(new BeanPropertyRowMapper<>(HCIntermediate.class));
	        return databaseReader;
	    }
	
	    private RowMapper<Employee> excelRowMapper() {
	    	 
	        return new EmployeeRowMapper();
	    }
	    
	   
	    @Bean
	    public ItemProcessor<Employee, HCDetails> processor() {
	        return new ProcessorHCMasterBuild();
	    }
	    
	    @Bean
	    public ItemProcessor<HCIntermediate, String> processor2() {
	        return new ProcessSetFileResource();
	    }

	    
	    
	    @Bean
	    public JpaItemWriter<HCDetails> writer() {
	        JpaItemWriter<HCDetails> writer = new JpaItemWriter<>();
	        writer.setEntityManagerFactory(emf);
	        return writer;
	    }
	    
	    @Bean
	    public JpaItemWriter<HCVersion> writer2() {
	    	JpaItemWriter<HCVersion> writer = new JpaItemWriter<>();
	        writer.setEntityManagerFactory(emf);
	    	return writer;
	    }


}
