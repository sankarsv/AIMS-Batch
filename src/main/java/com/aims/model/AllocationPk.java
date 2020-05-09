package com.aims.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class AllocationPk implements Serializable {

	@Column(name = "employee_id", nullable = false)
	private int employeeId;

	@Column(name = "project_id", nullable = false)
	private int projectId;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	public AllocationPk() {

	}

	public AllocationPk(int employeeId, int projectId, LocalDate startDate) {
		this.employeeId = employeeId;
		this.projectId = projectId;
		this.startDate = startDate;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeId;
		result = prime * result + projectId;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		if (employeeId != other.employeeId)
			return false;
		if (projectId != other.projectId)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AllocationNewPk [employeeId=" + employeeId + ", projectId=" + projectId + ", startDate=" + startDate
				+ "]";
	}

}
