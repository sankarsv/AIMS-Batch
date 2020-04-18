/**
 * 
 */
package com.aims.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ShanmugapriyaJ
 *
 */
@Entity
@Table(name="hc_intermediate",schema="aims")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HCIntermediate {
	@Id 
	@Column(name="EMPLOYEE_ID")
	private int employeeId;
	
	@Column(name="JSON",length = 10000)
	private String json;

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the json
	 */
	public String getJson() {
		return json;
	}

	/**
	 * @param json the json to set
	 */
	public void setJson(String json) {
		this.json = json;
	}

	
	
}
