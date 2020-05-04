package com.aims.model;

import java.io.Serializable;

public class BillingMasterPK implements Serializable {
	
	protected Integer versionNo;

	protected String empId;

	public BillingMasterPK() {
	}

	public BillingMasterPK(Integer versionNo, String empId) {
		this.versionNo = versionNo;
		this.empId = empId;
	}



}
