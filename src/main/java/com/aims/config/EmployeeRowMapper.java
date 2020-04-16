package com.aims.config;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import com.aims.bo.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	
	@Override
	public Employee mapRow(RowSet rs) throws Exception {
		if (rs == null || rs.getCurrentRow() == null) {
			return null;
		}
		Employee emp = new Employee();
		
		emp.setWorkGeography(rs.getColumnValue(0));

		
		System.out.println(rs.getColumnValue(0));
		

		return emp;
	}

}
