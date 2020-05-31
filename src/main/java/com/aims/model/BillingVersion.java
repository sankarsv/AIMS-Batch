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
	@Column(name = "BRM_EMPNO")
	private String brmEmpNo;
	
	@Id
	@Column(name = "PERIODMONTH")
	private String month;
	
	@Id
	@Column(name = "YEAR")
	private int year;
	
	@Id
	@Column(name = "LOCATION")
	private String location;
	
			
	/*@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequenceName", value = "aims.SEQ_BILL_VERSION"),
            @org.hibernate.annotations.Parameter(name = "allocationSize", value = "1"),
    })
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")*/
    //@SequenceGenerator(sequenceName = "aims.SEQ_BILL_VERSION", allocationSize = 1, name = "CUST_SEQ")
	@Column(name = "VERSION")
	private int versionNo;
	
	@Column(name = "FREEZEIND")
	private String freezeInd;
	
	@Column(name = "DRAFTINDICATOR")
	private String draftInd;

	@Column(name = "BILLINGCOMMENTS")
	private String billingComments;
	
	@Column(name = "CLARITYVERSION")
	private int clarityVersion;
	
	@Column(name = "DISCREPANCYVERSION")
	private int discrepancyVersion;

	
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBrmEmpNo() {
		return brmEmpNo;
	}

	public void setBrmEmpNo(String brmEmpNo) {
		this.brmEmpNo = brmEmpNo;
	}

	public String getDraftInd() {
		return draftInd;
	}

	public void setDraftInd(String draftInd) {
		this.draftInd = draftInd;
	}

	public String getBillingComments() {
		return billingComments;
	}

	public void setBillingComments(String billingComments) {
		this.billingComments = billingComments;
	}

	public int getClarityVersion() {
		return clarityVersion;
	}

	public void setClarityVersion(int clarityVersion) {
		this.clarityVersion = clarityVersion;
	}

	public int getDiscrepancyVersion() {
		return discrepancyVersion;
	}

	public void setDiscrepancyVersion(int discrepancyVersion) {
		this.discrepancyVersion = discrepancyVersion;
	}
	
}
