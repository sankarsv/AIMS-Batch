package com.aims.config;

import javax.persistence.EntityManagerFactory;

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
import org.springframework.core.io.ClassPathResource;

import com.aims.bo.Employee;
import com.aims.listener.JobCompletionListener;
import com.aims.mapper.EmployeeRowMapper;
import com.aims.model.HCIntermediate;
import com.aims.model.HCVersion;
import com.aims.processor.ProcessSetVersionValues;
import com.aims.processor.ProcessorConvertToJson;
import com.aims.step.Reader;

@Configuration
public class BatchConfig {

	
	  @Autowired public JobBuilderFactory jobBuilderFactory;
	  
	  @Autowired public StepBuilderFactory stepBuilderFactory;

	  
	    @Autowired
	    private EntityManagerFactory emf;
	 

	/*
	 * @Bean public Job processJob() { return jobBuilderFactory.get("processJob")
	 * .incrementer(new RunIdIncrementer()).listener(listener())
	 * .flow(orderStep1()).end().build(); }
	 * 
	 * @Bean public Step orderStep1() { return
	 * stepBuilderFactory.get("orderStep1").<String, String> chunk(1) .reader(new
	 * Reader()).processor(new Processor()) .writer(new Writer()).build(); }
	 * 
	 * @Bean public JobExecutionListener listener() { return new
	 * JobCompletionListener(); }
	 */
	
	
	
	 @Bean public Job processJob() { return jobBuilderFactory.get("processJob")
	 .incrementer(new RunIdIncrementer()).listener(listener())
	 .flow(orderStep1()).next(orderStep2()).end().build(); }
	 
	 @Bean public Step orderStep1() { return
	 stepBuilderFactory.get("orderStep1").<Employee, HCIntermediate> chunk(1) .reader(excelStudentReader())
	 .processor(processor())
	 .writer(writer())
	 .build(); }
	 
	 @Bean public Step orderStep2() { return
			 stepBuilderFactory.get("orderStep2").<String, HCVersion> chunk(1).reader(hcVersionReader())
			 .processor(processor2())
			 .writer(writer2())
			 .build(); }
	 
	 @Bean public JobExecutionListener listener() { return new
			 JobCompletionListener(); }
	 @Bean
	    ItemReader<Employee> excelStudentReader() {
	        PoiItemReader<Employee> reader = new PoiItemReader<Employee>();
	        System.out.println("Entered configuration");
	        reader.setUseDataFormatter(true);
	        reader.setLinesToSkip(16);
	        reader.setResource(new ClassPathResource("MasterFeed.xls"));
	        reader.setRowMapper(excelRowMapper());
	        return reader;
	    }
	
	 @Bean
	    ItemReader<String> hcVersionReader() {        
	        System.out.println("Entered hc version reader");
	        return new Reader();
	    }
	
	/* @Bean
	    ItemReader<String> readDescription() {
	        PoiItemReader<String> reader = new PoiItemReader<String>();
	        System.out.println("Entered configuration");
//	        reader.setUseDataFormatter(true);
	        reader.setResource(new ClassPathResource("MasterFeed.xls"));
	        reader.setRowMapper(descriptionRowMapper());
	        return reader;
	    }
	*/ 
	    private RowMapper<Employee> excelRowMapper() {
	    	 
	        return new EmployeeRowMapper();
	    }
	    
	   
	    @Bean
	    public ItemProcessor<Employee, HCIntermediate> processor() {
	        return new ProcessorConvertToJson();
	    }
	    
	    @Bean
	    public ItemProcessor<String, HCVersion> processor2() {
	        return new ProcessSetVersionValues();
	    }

	    
	    
	    @Bean
	    public JpaItemWriter<HCIntermediate> writer() {
	        JpaItemWriter writer = new JpaItemWriter();
	        writer.setEntityManagerFactory(emf);
	        return writer;
	    }
	    
	    @Bean
	    public JpaItemWriter<HCVersion> writer2() {
	        JpaItemWriter writer = new JpaItemWriter();
	        writer.setEntityManagerFactory(emf);
	        return writer;
	    }


}
