package com.aims.mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aims.bo.ClarityDetails;

public class ClarityRowMapper implements RowMapper<ClarityDetails> {

	private Map<String, String> tempStore = null;

	public ClarityRowMapper() {
		tempStore = new HashMap<String, String>();
		tempStore.put("trans_class", null);
		tempStore.put("cccio", null);
		tempStore.put("resource_manager", null);
		tempStore.put("timesheet_dept", null);
		tempStore.put("fullname", null);
		tempStore.put("resourceId", null);
	}

	@Override
	public ClarityDetails mapRow(RowSet rs) throws Exception {

		ClarityDetails cd = new ClarityDetails();

		if (rs == null || rs.getCurrentRow() == null) {
			return null;
		}

		if (rs.getColumnValue(0) == null || rs.getColumnValue(0)=="") {
			cd.setTransactionClass(tempStore.get("trans_class"));
		} else {
			cd.setTransactionClass(rs.getColumnValue(0));
			tempStore.put("trans_class", rs.getColumnValue(0));
		}
		if (rs.getColumnValue(1) == null || rs.getColumnValue(1)=="") {
			cd.setCccio(tempStore.get("cccio"));
		} else {
			cd.setCccio(rs.getColumnValue(1));
			tempStore.put("cccio", rs.getColumnValue(1));
		}
		if (rs.getColumnValue(2) == null || rs.getColumnValue(2)=="") {
			cd.setResourceManager(tempStore.get("resource_manager"));
		} else {
			cd.setResourceManager(rs.getColumnValue(2));
			tempStore.put("resource_manager", rs.getColumnValue(2));
		}
		if (rs.getColumnValue(3) == null || rs.getColumnValue(3)=="") {
			cd.setTimesheetDept(tempStore.get("timesheet_dept"));
		} else {
			cd.setTimesheetDept(rs.getColumnValue(3));
			tempStore.put("timesheet_dept", rs.getColumnValue(3));
		}
		if (rs.getColumnValue(4) == null || rs.getColumnValue(4)=="") {
			cd.setFullName(tempStore.get("fullname"));
		} else {
			cd.setFullName(rs.getColumnValue(4));
			tempStore.put("fullname", rs.getColumnValue(4));
		}
		if (rs.getColumnValue(5) == null || rs.getColumnValue(5)=="") {
			cd.setResourceId(tempStore.get("resourceId"));
		} else {
			cd.setResourceId(rs.getColumnValue(5));
			tempStore.put("resourceId", rs.getColumnValue(5));
		}

		String[] officeId = cd.getResourceId().split("_");
		cd.setOfficeId(officeId[0]);
		cd.setCin(rs.getColumnValue(6));
		cd.setSumOfHrs(rs.getColumnValue(7));
		cd.setAvgRate(rs.getColumnValue(8));
		cd.setRateWoTax(rs.getColumnValue(9));

		return cd;

	}
}
