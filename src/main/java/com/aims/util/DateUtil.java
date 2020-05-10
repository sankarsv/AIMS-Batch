package com.aims.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class DateUtil {

	public static Date parseDate(String date) throws Exception {
		try {
			String testDate =  date;
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			return formatter.parse(testDate);
		}catch(Exception e) {
			throw new Exception("Invalid date provided. Please provide date in dd-MM-yyyy");
		}
	}
	
}
