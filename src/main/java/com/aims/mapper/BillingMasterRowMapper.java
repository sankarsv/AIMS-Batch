package com.aims.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import com.aims.bo.BillingDetails;

public class BillingMasterRowMapper implements RowMapper<BillingDetails> {

	@Override
	public BillingDetails mapRow(RowSet rs) throws Exception {
		
		
		if (rs == null || rs.getCurrentRow() == null || !rs.getMetaData().getSheetName().equalsIgnoreCase("Sheet1")
				) {
			return null;
		}
		if( rs.getColumnValue(0)=="")
			return new BillingDetails();
		
		BillingDetails details = new BillingDetails();
		details.setBrm(rs.getColumnValue(0));
		details.setDmName(rs.getColumnValue(1));
		details.setOnsiteOffshore(rs.getColumnValue(2));
		details.setWonNumber(rs.getColumnValue(3));
		details.setProjectName(rs.getColumnValue(4));
		details.setSto(rs.getColumnValue(5));
		details.setEmpId(rs.getColumnValue(6));
		details.setOfficeId(rs.getColumnValue(7));
		details.setEmpName(rs.getColumnValue(8));
		details.setBillRate(rs.getColumnValue(9));
		if(null!=rs.getColumnValue(10))	
		details.setBillablehrs(Integer.parseInt(rs.getColumnValue(10)));
		if(null!=rs.getColumnValue(11))
		details.setBillableDays(getScaledValue(rs.getColumnValue(11)));
		if(null!=rs.getColumnValue(12))
		details.setEffort(getScaledValue(rs.getColumnValue(12)));
		if(null!=rs.getColumnValue(13) && !"".equals(rs.getColumnValue(13)))
		details.setExtraHours(getScaledValue(rs.getColumnValue(13)));
		if(null!=rs.getColumnValue(14))
		details.setExtraBilling(getScaledValue(rs.getColumnValue(14)));
		if(null!=rs.getColumnValue(15)) {
		details.setBillableAmount(getScaledValue(rs.getColumnValue(15)));
		}
		details.setRemarks1(rs.getColumnValue(16));
		details.setRemarks2(rs.getColumnValue(17));
		
		return details;
	}
	
	private float getScaledValue(String origValue)
	{
		BigDecimal value = new BigDecimal(Float.parseFloat(origValue));
	    value = value.setScale(2, RoundingMode.HALF_UP); 
	    return value.floatValue();
	}
}
