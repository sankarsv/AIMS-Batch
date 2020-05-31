package com.aims.model;

import java.io.Serializable;

public class BillingVersionPK implements Serializable{


	protected String brmEmpNo;
	
	protected String month;
	
	protected String location;
	
	protected int year;
	
	public BillingVersionPK() {
	}

	public BillingVersionPK(String brmEmpNo, String month, int year, String location) {
		
		this.brmEmpNo =brmEmpNo;
		this.month=month;
		this.year = year;
		this.location=location;
	}
	
	

}
