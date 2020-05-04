package com.aims.processor;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemProcessor;

import com.aims.bo.BillingDetails;
import com.aims.dao.BatchDao;
import com.aims.model.BillingVersion;

public class ProcessBillingVersionBuild implements ItemProcessor<BillingDetails, BillingVersion> {
	
	private DataSource datasource;
	
	private boolean isVersionInsert=false;
	
	public ProcessBillingVersionBuild(DataSource datasource) {
		this.datasource=datasource;
	}

	@Override
	public BillingVersion process(BillingDetails details) throws Exception {
		// TODO Auto-generated method stub

		BillingVersion vers = new BillingVersion();

		mapBillingVersion(details, vers);
		
		if(!isVersionInsert)
		{
			new BatchDao(datasource).insertBillingVersion(vers);
			isVersionInsert=true;
		}

		return vers;
	}

	private void mapBillingVersion(BillingDetails bd, BillingVersion bv) {

		bv.setBrmRef(bd.getBrm());
		bv.setYear(bd.getBillYear());
		bv.setMonth(bd.getBillMonth());
		bv.setVersionNo(0);
		bv.setFreezeInd("N");
	}

}
