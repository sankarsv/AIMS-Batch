package com.aims.mapper;

import java.time.LocalDate;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import com.aims.bo.BillingDetails;

public class BillingRowMapper implements RowMapper<BillingDetails> {

	private BillingDetails details = null;

	public BillingRowMapper() {
		details = new BillingDetails();
	}

	@Override
	public BillingDetails mapRow(RowSet rs) throws Exception {

		if (rs == null || rs.getCurrentRow() == null 
				|| rs.getCurrentRowIndex() > 2
				|| !rs.getMetaData().getSheetName().equalsIgnoreCase("Sheet1")) {
			return null;
		}

		details.setBrm(rs.getColumnValue(0));

		LocalDate currentDate = LocalDate.now();

		details.setBillMonth(currentDate.getMonth().toString());
		details.setBillYear(currentDate.getYear());

		return details;
	}

}
