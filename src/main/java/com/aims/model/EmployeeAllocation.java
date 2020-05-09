package com.aims.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "allocation", schema = "aims")
public class EmployeeAllocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AllocationPk allocation_pk;

	/*
	 * @Column(name = "employee_id") private int employeeId;
	 * 
	 * @Column(name = "start_date") private LocalDate startDate;
	 * 
	 * @Column(name = "project_id") private int projectId;
	 */

	@Column(name = "won_id")
	private int wonId;

	@Column(name = "project_change_date")
	private LocalDate projectChangeDate;

	@Column(name = "portfolio_id")
	private int portfolioId;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "travel_type")
	private String travelType;

	@Column(name = "percentage_allocation")
	private float percentageAllocation;

	public int getWonId() {
		return wonId;
	}

	public void setWonId(int wonId) {
		this.wonId = wonId;
	}

	public LocalDate getProjectChangeDate() {
		return projectChangeDate;
	}

	public void setProjectChangeDate(LocalDate projectChangeDate) {
		this.projectChangeDate = projectChangeDate;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public float getPercentageAllocation() {
		return percentageAllocation;
	}

	public void setPercentageAllocation(float percentageAllocation) {
		this.percentageAllocation = percentageAllocation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AllocationPk getAllocationNew_pk() {
		return allocation_pk;
	}

	public void setAllocationNew_pk(AllocationPk allocationNew_pk) {
		this.allocation_pk = allocationNew_pk;
	}

	public EmployeeAllocation(AllocationPk allocationNew_pk, int wonId, LocalDate projectChangeDate, int portfolioId,
			LocalDate endDate, String travelType, float percentageAllocation) {
		super();
		this.allocation_pk = allocationNew_pk;
		this.wonId = wonId;
		this.projectChangeDate = projectChangeDate;
		this.portfolioId = portfolioId;
		this.endDate = endDate;
		this.travelType = travelType;
		this.percentageAllocation = percentageAllocation;
	}

	@Override
	public String toString() {
		return "EmployeeAllocation [allocationNew_pk=" + allocation_pk + ", wonId=" + wonId + ", projectChangeDate="
				+ projectChangeDate + ", portfolioId=" + portfolioId + ", endDate=" + endDate + ", travelType="
				+ travelType + ", percentageAllocation=" + percentageAllocation + "]";
	}

	public EmployeeAllocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
