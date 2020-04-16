package com.aims.step;

import org.springframework.batch.item.ItemProcessor;

import com.aims.bo.Employee;

public class Processor implements ItemProcessor<Employee, Employee> {

	@Override
	public Employee process(Employee data) throws Exception {
		return data;
	}

}
