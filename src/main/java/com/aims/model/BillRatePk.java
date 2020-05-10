package com.aims.model;

import java.io.Serializable;

public class BillRatePk implements Serializable {

	protected String empId;

	protected int version;

	public BillRatePk() {
	}

	public BillRatePk(String empId, int version) {

		this.empId = empId;
		this.version = version;
	}
}
