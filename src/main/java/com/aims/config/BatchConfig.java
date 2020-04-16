package com.aims.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.aims.bo.Employee;
import com.aims.listener.JobCompletionListener;
import com.aims.step.Processor;
import com.aims.step.Reader;
import com.aims.step.Writer;

@Configuration
public class BatchConfig {

	
	  @Autowired public JobBuilderFactory jobBuilderFactory;
	  
	  @Autowired public StepBuilderFactory stepBuilderFactory;
	 

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
	 .flow(orderStep1()).end().build(); }
	 
	 @Bean public Step orderStep1() { return
	 stepBuilderFactory.get("orderStep1").<Employee, Employee> chunk(1) .reader(excelStudentReader()).processor(new Processor()) .writer(new Writer()).build(); }
	 
	 @Bean public JobExecutionListener listener() { return new
			 JobCompletionListener(); }
	 @Bean
	    ItemReader<Employee> excelStudentReader() {
	        PoiItemReader<Employee> reader = new PoiItemReader<Employee>();
	        System.out.println("Entered configuration");
	        reader.setLinesToSkip(16);
	        reader.setResource(new ClassPathResource("MasterFeed.xls"));
	        reader.setRowMapper(excelRowMapper());
	        return reader;
	    }
	 
	    private RowMapper<Employee> excelRowMapper() {
	        
	        return new EmployeeRowMapper();
	    }

}
