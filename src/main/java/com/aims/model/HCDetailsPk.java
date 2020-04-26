package com.aims.model;

import java.io.Serializable;

public class HCDetailsPk implements Serializable {

	protected Integer versionNo;

	protected Integer empNo;

	public HCDetailsPk() {
	}

	public HCDetailsPk(Integer versionNo, Integer empNo) {
		this.versionNo = versionNo;
		this.empNo = empNo;
	}

	
}
