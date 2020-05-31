package com.aims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CLARITYMASTER", schema = "aims")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ClarityMaster {
	
	@Id
	@Column(name = "CLARITY_ID")
	private int clarityId;
	
	@Column(name = "VERSION")
	private int version;
	
	@Column(name = "TRANSACTIONCLASS")
	private String transactionClass;	
	
	@Column(name = "CCCIO")
	private String cccio;
	
	@Column(name = "RESOURCEMANAGER")
	private String resourceManager;
	
	@Column(name = "TIMESHEETDEPARTMENT")
	private String timesheetDept;
	
	@Column(name = "LASTNAMEFIRSTNAME")
	private String fullName;
	
	@Column(name = "RESOURCEID")
	private String resourceId;
	
	@Column(name = "OFFICEID")
	private String officeId;
	
	@Column(name = "CIN")
	private String cin;
	
	@Column(name = "SUMOFHOURS")
	private String sumOfHrs;
	
	@Column(name = "AVERAGERATE")
	private String avgRate;
	
	@Column(name = "RATEWITHOUTTAX")
	private String rateWoTax;

	public int getClarityId() {
		return clarityId;
	}

	public void setClarityId(int clarityId) {
		this.clarityId = clarityId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTransactionClass() {
		return transactionClass;
	}

	public void setTransactionClass(String transactionClass) {
		this.transactionClass = transactionClass;
	}

	public String getCccio() {
		return cccio;
	}

	public void setCccio(String cccio) {
		this.cccio = cccio;
	}

	public String getResourceManager() {
		return resourceManager;
	}

	public void setResourceManager(String resourceManager) {
		this.resourceManager = resourceManager;
	}

	public String getTimesheetDept() {
		return timesheetDept;
	}

	public void setTimesheetDept(String timesheetDept) {
		this.timesheetDept = timesheetDept;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getSumOfHrs() {
		return sumOfHrs;
	}

	public void setSumOfHrs(String sumOfHrs) {
		this.sumOfHrs = sumOfHrs;
	}

	public String getAvgRate() {
		return avgRate;
	}

	public void setAvgRate(String avgRate) {
		this.avgRate = avgRate;
	}

	public String getRateWoTax() {
		return rateWoTax;
	}

	public void setRateWoTax(String rateWoTax) {
		this.rateWoTax = rateWoTax;
	}


}
