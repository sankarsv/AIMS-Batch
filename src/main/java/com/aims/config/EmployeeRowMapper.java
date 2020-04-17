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
		emp.setWorkCountry(rs.getColumnValue(1));
		emp.setWorkLocation(rs.getColumnValue(2));
		emp.setClientGeography(rs.getColumnValue(3));
		emp.setClientCountry(rs.getColumnValue(4));
		emp.setIP(rs.getColumnValue(5));
		emp.setSP(rs.getColumnValue(6));
		emp.setSubSP(rs.getColumnValue(7));
		emp.setCustomer(rs.getColumnValue(8));
		emp.setGroupCustomer(rs.getColumnValue(9));
		emp.setRm(rs.getColumnValue(10));
		emp.setBrmNo(rs.getColumnValue(11));
		emp.setBrmName(rs.getColumnValue(12));
		emp.setGlName(rs.getColumnValue(13));
		emp.setAmNumber(rs.getColumnValue(14));
		emp.setAMName(rs.getColumnValue(15));
		emp.setProjectNumber(rs.getColumnValue(16));
		emp.setProjectName(rs.getColumnValue(17));
		emp.setProjectLocation(rs.getColumnValue(18));
		emp.setProjectType(rs.getColumnValue(19));
		emp.setTurnkeyFlag(rs.getColumnValue(20));
		emp.setSwonCategory(rs.getColumnValue(21));
		emp.setProjectCluster(rs.getColumnValue(22));
		emp.setIOU(rs.getColumnValue(23));
		emp.setSubIOU(rs.getColumnValue(24));
		emp.setEmpId(rs.getColumnValue(25));
		emp.setEmployeeName(rs.getColumnValue(26));
		emp.setDoj(rs.getColumnValue(27));
		emp.setDeputeBranch(rs.getColumnValue(28));
		emp.setDeputeDC(rs.getColumnValue(29));
		emp.setEmployeeLocation(rs.getColumnValue(30));
		emp.setEmployeeBaseCountry(rs.getColumnValue(31));
		emp.setBaseBranch(rs.getColumnValue(32));
		emp.setBaseDC(rs.getColumnValue(33));
		emp.setEmployeeTravelCountry(rs.getColumnValue(34));
		emp.setTravelType(rs.getColumnValue(35));
		emp.setDesignation(rs.getColumnValue(36));
		emp.setGrade(rs.getColumnValue(37));
		emp.setMappDesignation(rs.getColumnValue(38));
		emp.setSeniorJunior(rs.getColumnValue(39));
		emp.setCCNonCC(rs.getColumnValue(40));
		emp.setPersonType(rs.getColumnValue(41));
		emp.setSubPersonType(rs.getColumnValue(42));
		emp.setSobName(rs.getColumnValue(43));
		emp.setTCSExp(rs.getColumnValue(44));
		emp.setTotalExp(rs.getColumnValue(45));
		emp.setAllocationStartDate(rs.getColumnValue(46));
		emp.setAllocationEndDate(rs.getColumnValue(47));
		emp.setPercentageAllocation(rs.getColumnValue(48));
		emp.setEmployeeCluster(rs.getColumnValue(49));
		emp.setParentIOU(rs.getColumnValue(50));
		emp.setChildIOU(rs.getColumnValue(51));
		emp.setTeamRole(rs.getColumnValue(52));	
		System.out.println(rs.getColumnValue(0));
		return emp;
	}

}
