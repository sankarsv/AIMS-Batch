package com.aims.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "BILLINGMASTER", schema = "aims")
@IdClass(BillingMasterPK.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class BillingMaster implements Serializable {

	@Id
	@Column(name = "VERSION")
	private int versionNo;

	@Id
	@Column(name = "EMPLOYEE_ID")
	private String empId;

	@Column(name = "DMNAME")
	private String dmName;
	
	@Column(name = "PROJECTNAME")
	private String projectName;
	
	/*@Column(name="BRMNAME")
	private String brmName;
	
	@Column(name="EMPLOYEE_NAME")
	private String empName;
	
	@Column(name="OFFICEID")
	private String officeId;*/

	@Column(name = "WON")
	private String wonNumber;

	@Column(name = "ONSITE_OFFSHORE")
	private String onsiteOffshore;

	@Column(name = "STO")
	private String sto;

	@Column(name = "BILLABLEHOURS")
	private int billablehrs;

	@Column(name = "BILLABLEDAYS")
	private float billableDays;

	@Column(name = "EFFORT")
	private float effort;

	@Column(name = "EXTRAHOURS")
	private float extraHours;

	@Column(name = "EXTRABILLING")
	private float extraBilling;

	@Column(name = "BILLABLEAMOUNT")
	private float billableAmount;

	@Column(name = "REMARKS1")
	private String remarks1;

	@Column(name = "REMARKS2")
	private String remarks2;

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getDmName() {
		return dmName;
	}

	public void setDmName(String dmName) {
		this.dmName = dmName;
	}

	public String getWonNumber() {
		return wonNumber;
	}

	public void setWonNumber(String wonNumber) {
		this.wonNumber = wonNumber;
	}

	public String getOnsiteOffshore() {
		return onsiteOffshore;
	}

	public void setOnsiteOffshore(String onsiteOffshore) {
		this.onsiteOffshore = onsiteOffshore;
	}

	public String getSto() {
		return sto;
	}

	public void setSto(String sto) {
		this.sto = sto;
	}

	public int getBillablehrs() {
		return billablehrs;
	}

	public void setBillablehrs(int billablehrs) {
		this.billablehrs = billablehrs;
	}

	public float getBillableDays() {
		return billableDays;
	}

	public void setBillableDays(float billableDays) {
		this.billableDays = billableDays;
	}

	public float getEffort() {
		return effort;
	}

	public void setEffort(float effort) {
		this.effort = effort;
	}

	public float getExtraHours() {
		return extraHours;
	}

	public void setExtraHours(float extraHours) {
		this.extraHours = extraHours;
	}

	public float getExtraBilling() {
		return extraBilling;
	}

	public void setExtraBilling(float extraBilling) {
		this.extraBilling = extraBilling;
	}

	public float getBillableAmount() {
		return billableAmount;
	}

	public void setBillableAmount(float billableAmount) {
		this.billableAmount = billableAmount;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/*public String getBrmName() {
		return brmName;
	}

	public void setBrmName(String brmName) {
		this.brmName = brmName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}*/
}
