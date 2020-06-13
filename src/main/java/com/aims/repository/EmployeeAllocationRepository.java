package com.aims.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aims.model.AllocationPk;
import com.aims.model.EmployeeAllocation;

@Repository
public interface EmployeeAllocationRepository<E> extends JpaRepository<EmployeeAllocation, AllocationPk> {
	
	@Query("SELECT u FROM EmployeeAllocation u WHERE employeeId = :employeeId")
    List<EmployeeAllocation> findByEmployeeId(@Param("employeeId") Integer employeeId);
	
	
}
