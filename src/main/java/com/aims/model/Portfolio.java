package com.aims.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="portfolio",schema="aims")
public class Portfolio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="portfolio_id" )
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer portfolioId;
	
	@Column(name="portfolio_name" )
    private String portfolioName;

	@Column(name="portfolio_type" )
    private String portfolioType;
	
	@Column(name="location" )
    private String location;
	
	@Column(name="sto" )
    private String sto;
	
	@Column(name="lto" )
    private String lto;
	
	@Column(name="brm_empid" )
	@NotNull
    private Integer brmEmpId;
	
	@Column(name="brmname" )
    private String brmName;
	
	@Column(name="brmnamehc" )
    private String brmNameHC;
	
	@Column(name="brmnamebilling" )
    private String brmNameBilling;
	
	@Column(name="em_empno" )
	@NotNull
    private Integer emEmpNo;
	
	@Column(name="em_name" )
    private String emName;	

	@Column(name="emnamehc" )
    private String emNameHc;	

	@Column(name="emnamebilling" )
    private String emNameBilling;
	
	@Column(name="dm_emp_id" )
	@NotNull
    private Integer dmEmpId;
		
	@Column(name="dm_name" )
    private String dmName;	

	@Column(name="dmnamehc" )
    private String dmNameHc;	

	@Column(name="dmnamebilling" )
    private String dmNameBilling;
	
	@Column(name="billing_lead_emp_id" )
	@NotNull
    private Integer billingLeadEmpId;	
	
	@Column(name="billing_lead_name" )
    private String billingLeadName;	
	
	@Column(name="description" )
    private Integer description;

	public Integer getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getPortfolioType() {
		return portfolioType;
	}

	public void setPortfolioType(String portfolioType) {
		this.portfolioType = portfolioType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSto() {
		return sto;
	}

	public void setSto(String sto) {
		this.sto = sto;
	}

	public String getLto() {
		return lto;
	}

	public void setLto(String lto) {
		this.lto = lto;
	}

	public Integer getBrmEmpId() {
		return brmEmpId;
	}

	public void setBrmEmpId(Integer brmEmpId) {
		this.brmEmpId = brmEmpId;
	}

	public String getBrmName() {
		return brmName;
	}

	public void setBrmName(String brmName) {
		this.brmName = brmName;
	}

	public String getBrmNameHC() {
		return brmNameHC;
	}

	public void setBrmNameHC(String brmNameHC) {
		this.brmNameHC = brmNameHC;
	}

	public String getBrmNameBilling() {
		return brmNameBilling;
	}

	public void setBrmNameBilling(String brmNameBilling) {
		this.brmNameBilling = brmNameBilling;
	}

	public Integer getEmEmpNo() {
		return emEmpNo;
	}

	public void setEmEmpNo(Integer emEmpNo) {
		this.emEmpNo = emEmpNo;
	}

	public String getEmName() {
		return emName;
	}

	public void setEmName(String emName) {
		this.emName = emName;
	}

	public String getEmNameHc() {
		return emNameHc;
	}

	public void setEmNameHc(String emNameHc) {
		this.emNameHc = emNameHc;
	}

	public String getEmNameBilling() {
		return emNameBilling;
	}

	public void setEmNameBilling(String emNameBilling) {
		this.emNameBilling = emNameBilling;
	}

	public Integer getDmEmpId() {
		return dmEmpId;
	}

	public void setDmEmpId(Integer dmEmpId) {
		this.dmEmpId = dmEmpId;
	}

	public String getDmName() {
		return dmName;
	}

	public void setDmName(String dmName) {
		this.dmName = dmName;
	}

	public String getDmNameHc() {
		return dmNameHc;
	}

	public void setDmNameHc(String dmNameHc) {
		this.dmNameHc = dmNameHc;
	}

	public String getDmNameBilling() {
		return dmNameBilling;
	}

	public void setDmNameBilling(String dmNameBilling) {
		this.dmNameBilling = dmNameBilling;
	}

	public Integer getBillingLeadEmpId() {
		return billingLeadEmpId;
	}

	public void setBillingLeadEmpId(Integer billingLeadEmpId) {
		this.billingLeadEmpId = billingLeadEmpId;
	}

	public String getBillingLeadName() {
		return billingLeadName;
	}

	public void setBillingLeadName(String billingLeadName) {
		this.billingLeadName = billingLeadName;
	}

	public Integer getDescription() {
		return description;
	}

	public void setDescription(Integer description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

		
}
