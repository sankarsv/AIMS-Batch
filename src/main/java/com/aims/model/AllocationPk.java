package com.aims.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Embeddable
public class AllocationPk implements Serializable {

	
	@Column(name = "allocation_id", nullable = false)
	@SequenceGenerator(name="seq_allocation_id",schema = "aims", initialValue=1, allocationSize=100)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_allocation_id")
	private int allocationId;
	
	public AllocationPk() {

	}

	public AllocationPk(int allocationId) {
		this.allocationId = allocationId;
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + allocationId;		
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
		AllocationPk other = (AllocationPk) obj;
		if (allocationId != other.allocationId)
			return false;		
		return true;
	}

	@Override
	public String toString() {
		return "AllocationNewPk [allocationId=" + allocationId +"]";
	}

	public int getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(int allocationId) {
		this.allocationId = allocationId;
	}

}
