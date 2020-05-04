package com.aims.model;

import java.io.Serializable;

public class BillingVersionPK implements Serializable{


	protected String brmRef;
	
	protected String month;
	
	protected int year;
	
	public BillingVersionPK() {
	}

	public BillingVersionPK(String brmRef, String month, int year) {
		
		this.brmRef =brmRef;
		this.month=month;
		this.year = year;
	}

}
