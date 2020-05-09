package com.aims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aims.model.AllocationPk;
import com.aims.model.EmployeeAllocation;
import com.aims.repository.EmployeeAllocationRepository;


@Service
public class EmployeeAllocationService {

	@Autowired
	EmployeeAllocationRepository<EmployeeAllocation> employeeAllocationRepository;

	@Transactional
	public boolean addEmployeeAllocation(EmployeeAllocation project) {
		return employeeAllocationRepository.save(project) != null;
	}
	
	@Transactional
	public List<EmployeeAllocation> addEmployeeAllocations(List<EmployeeAllocation> EmployeeAllocations) {
		//return employeeRepository.save(employees) != null;
		List<EmployeeAllocation> response = (List<EmployeeAllocation>) employeeAllocationRepository.saveAll(EmployeeAllocations);
		return response;
	}

	@Transactional
	public boolean updateEmployeeAllocation(EmployeeAllocation project) {
		return employeeAllocationRepository.save(project) != null;
	}
	
	@Transactional
	public void deleteEmployeeAllocation(AllocationPk allocationPk) {
		employeeAllocationRepository.deleteById(allocationPk);
	}

	@Transactional
	public Optional<EmployeeAllocation> getById(AllocationPk allocationPk) {
		return employeeAllocationRepository.findById(allocationPk);
	}
	
	@Transactional
	public List<EmployeeAllocation> getByEmployeeId(Integer empId) {
		return employeeAllocationRepository.findByEmployeeId(empId);
	}

	@Transactional
	public List<EmployeeAllocation> getAllEmployeeAllocations() {
		return (List<EmployeeAllocation>) employeeAllocationRepository.findAll();
	}
	
	

}