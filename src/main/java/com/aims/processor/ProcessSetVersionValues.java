package com.aims.processor;

import org.springframework.batch.item.ItemProcessor;

import com.aims.bo.Employee;
import com.aims.model.HCVersion;

public class ProcessSetVersionValues implements ItemProcessor<String, HCVersion> {
	
	@Override
	public HCVersion process(String desc) throws Exception {
		
		HCVersion hcVers = new HCVersion();
		
		hcVers.setVersionNo(2);
		
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		System.out.println("current date"+ date);  
		hcVers.setLoad_Date(date);
		
		hcVers.setDescription(desc);
		
		hcVers.setCurrent_ind("Y");
		
		return hcVers;
		
	}

}
