package com.aims.step;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<String> {

	

	private int count = 0;

	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {

		return "Head Count Report";	}

}