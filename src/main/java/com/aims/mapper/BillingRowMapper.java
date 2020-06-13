package com.aims.mapper;

import java.time.LocalDate;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import com.aims.bo.BillingDetails;

public class BillingRowMapper implements RowMapper<BillingDetails> {


	public BillingRowMapper() {
	}

	@Override
	public BillingDetails mapRow(RowSet rs) throws Exception {
		
		BillingDetails details =  new BillingDetails();

		if (rs == null || rs.getCurrentRow() == null 
				
				|| (rs.getMetaData().getSheetName()!=null && !rs.getMetaData().getSheetName().equalsIgnoreCase("Sheet1"))
				) {
			return null;
		}
		
		if( rs.getColumnValue(0)=="")
			return new BillingDetails();

		details.setBrm(rs.getColumnValue(0));
		details.setOnsiteOffshore(rs.getColumnValue(2));

		LocalDate currentDate = LocalDate.now();

		details.setBillMonth(currentDate.getMonth().toString());
		details.setBillYear(currentDate.getYear());

		return details;
	}

}
