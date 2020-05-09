package com.aims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aims.model.Employee;
import com.aims.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository<Employee> employeeRepository;

	@Transactional
	public boolean addEmployee(Employee employee) {
		return employeeRepository.save(employee) != null;
	}
	
	@Transactional
	public List<Employee> addEmployees(List<Employee> employees) {
		//return employeeRepository.save(employees) != null;
		List<Employee> response = (List<Employee>) employeeRepository.saveAll(employees);
		return response;
	}

	@Transactional
	public boolean updateEmployee(Employee employee) {
		return employeeRepository.save(employee) != null;
	}
	
	@Transactional
	public void deleteEmployee(Integer empId) {
		employeeRepository.deleteById(empId);
	}

	@Transactional
	public Optional<Employee> getById(Integer empId) {
		return employeeRepository.findById(empId);
	}

	@Transactional
	public List<Employee> getAllEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}
	
	

}