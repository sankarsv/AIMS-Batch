package com.aims.mapper;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;
import org.springframework.beans.factory.annotation.Autowired;

import com.aims.bo.Employee;
import com.aims.dao.BatchDao;

public class EmployeeRowMapper implements RowMapper<Employee> {
	
	public Integer versionNo;
	
	private boolean isVersionSet = false;
	
	private BatchDao dao;
	
	public EmployeeRowMapper(BatchDao dao)
	{
		this.dao=dao;
	}
	
		
	@Override
	public Employee mapRow(RowSet rs) throws Exception {
		
		if (rs == null || rs.getCurrentRow() == null || rs.getMetaData().getColumnCount()<50) {
			return null;
		}
		
		if(!isVersionSet)
		{
			versionNo = dao.getVersionNo();
			isVersionSet = true;
		}
		Employee emp = new Employee();
		
		emp.setWorkGeography(rs.getColumnValue(0));
		emp.setWorkCountry(rs.getColumnValue(1));
		emp.setWorkLocation(rs.getColumnValue(2));
		emp.setClientGeography(rs.getColumnValue(3));
		emp.setClientCountry(rs.getColumnValue(4));
		emp.setVersionNo(versionNo.toString());
		
		emp.setIP(rs.getColumnValue(5));
		emp.setSP(rs.getColumnValue(6));
		emp.setSubSP(rs.getColumnValue(7));		
		emp.setCustomer(rs.getColumnValue(8));
		emp.setGroupCustomer(rs.getColumnValue(9));
		
		emp.setRm(rs.getColumnValue(10));		
		emp.setBrmName(rs.getColumnValue(11));
		emp.setGlName(rs.getColumnValue(12));
		emp.setAmNumber(rs.getColumnValue(13));
		emp.setAMName(rs.getColumnValue(14));
		emp.setProjectNumber(rs.getColumnValue(15));
		
		emp.setPl(rs.getColumnValue(16));
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

		emp.setBrmEmName(rs.getColumnValue(27));
		emp.setDm(rs.getColumnValue(29));
		emp.setExpat(rs.getColumnValue(30));
		
		emp.setDoj(rs.getColumnValue(31));
		emp.setDeputeBranch(rs.getColumnValue(32));
		emp.setDeputeDC(rs.getColumnValue(33));
		emp.setEmployeeLocation(rs.getColumnValue(34));
		emp.setEmployeeBaseCountry(rs.getColumnValue(35));
		emp.setBaseBranch(rs.getColumnValue(36));
		emp.setBaseDC(rs.getColumnValue(37));
		emp.setEmployeeTravelCountry(rs.getColumnValue(38));
		
		emp.setTravelType(rs.getColumnValue(39));
		emp.setDesignation(rs.getColumnValue(40));
		emp.setGrade(rs.getColumnValue(41));
		emp.setMappDesignation(rs.getColumnValue(42));
		emp.setSeniorJunior(rs.getColumnValue(43));
		emp.setCCNonCC(rs.getColumnValue(44));
		emp.setPersonType(rs.getColumnValue(45));
		
		emp.setSubPersonType(rs.getColumnValue(46));
		emp.setSobName(rs.getColumnValue(47));
		emp.setTCSExp(rs.getColumnValue(48));
		emp.setTotalExp(rs.getColumnValue(49));
		emp.setAllocationStartDate(rs.getColumnValue(50));
		emp.setAllocationEndDate(rs.getColumnValue(51));
		emp.setPercentageAllocation(rs.getColumnValue(52));
		emp.setEmployeeCluster(rs.getColumnValue(53));
		emp.setParentIOU(rs.getColumnValue(54));
		emp.setChildIOU(rs.getColumnValue(55));
		emp.setTeamRole(rs.getColumnValue(56));	
		
		emp.setPlatforms(rs.getColumnValue(57));
		emp.setDc(rs.getColumnValue(58));
		emp.setSite(rs.getColumnValue(59));
		
		
		return emp;
	}

}
