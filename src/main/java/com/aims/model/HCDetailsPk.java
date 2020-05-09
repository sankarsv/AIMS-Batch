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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empNo;
		result = prime * result + versionNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HCDetailsPk other = (HCDetailsPk) obj;
		if (empNo != other.empNo)
			return false;
		if (versionNo != other.versionNo)
			return false;
		return true;
	}

	
}
