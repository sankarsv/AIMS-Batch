package com.aims.listener;

import org.springframework.batch.core.ItemReadListener;

import com.aims.bo.Employee;

public class HCDetailsItemReadListener implements ItemReadListener<Employee> {

	@Override
	public void beforeRead() {
		System.out.println("HC details reader about to be executed");
	}

	@Override
	public void afterRead(Employee e) {
		System.out.println("Employee details are read successfully from HCreport");
	}

	@Override
	public void onReadError(Exception ex) {
		System.out.println("An Exception has occured during read operation " + ex.getMessage());
	}

}
