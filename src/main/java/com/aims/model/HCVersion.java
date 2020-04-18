package com.aims.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="hcversion",schema="aims")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HCVersion implements Serializable{
	
	
	@Id 
	@Column(name="version_no")
	private int versionNo;
	
	@Id 
	@Column(name="load_date")
	private Date load_Date;
	
	@Id 
	@Column(name="description")
	private String description;
	
	@Id 
	@Column(name="current_ind")
	private String current_ind;

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public Date getLoad_Date() {
		return load_Date;
	}

	public void setLoad_Date(Date load_Date) {
		this.load_Date = load_Date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrent_ind() {
		return current_ind;
	}

	public void setCurrent_ind(String current_ind) {
		this.current_ind = current_ind;
	}

}
