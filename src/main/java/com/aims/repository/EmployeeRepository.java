package com.aims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aims.model.Employee;

@Repository
public interface EmployeeRepository<E> extends JpaRepository<Employee, Integer> {

}
