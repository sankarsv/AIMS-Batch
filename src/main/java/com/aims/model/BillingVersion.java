package com.aims.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.Query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "BILLINGVERSION", schema = "aims")
@IdClass(BillingVersionPK.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class BillingVersion implements Serializable{

	@Id
	@Column(name = "BRM")
	private String brmRef;
	
	@Id
	@Column(name = "PERIODMONTH")
	private String month;
	
	@Id
	@Column(name = "YEAR")
	private int year;
	
	@Column(name = "VERSION")		
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequenceName", value = "aims.SEQ_BILL_VERSION"),
            @org.hibernate.annotations.Parameter(name = "allocationSize", value = "1"),
    })
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    //@SequenceGenerator(sequenceName = "aims.SEQ_BILL_VERSION", allocationSize = 1, name = "CUST_SEQ")   
	private int versionNo;
	
	@Column(name = "FREEZEIND")
	private String freezeInd;

	public String getBrmRef() {	
		return brmRef;
	}

	public void setBrmRef(String brmRef) {
		this.brmRef = brmRef;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	@Query(value = "SELECT aims.seq_bill_version.nextval FROM dual", nativeQuery = 
		    true)
	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public String getFreezeInd() {
		return freezeInd;
	}

	public void setFreezeInd(String freezeInd) {
		this.freezeInd = freezeInd;
	}
	
}
