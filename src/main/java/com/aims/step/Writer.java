package com.aims.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.aims.bo.Employee;

public class Writer implements ItemWriter<Employee> {

	@Override
	public void write(List<? extends Employee> messages) throws Exception {
		
		
	}

}