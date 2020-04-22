package com.aims.step;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.aims.bo.Employee;

public class Reader implements ItemReader<String> {

	
	private boolean isReadComplete = false;
	


	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		
		return isReadComplete==false ? "Head Count Report": null;	
		
	}

}