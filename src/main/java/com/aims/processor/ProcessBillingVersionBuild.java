package com.aims.processor;

import org.springframework.batch.item.ItemProcessor;

import com.aims.bo.BillingDetails;
import com.aims.dao.BatchDao;
import com.aims.model.BillingVersion;

public class ProcessBillingVersionBuild implements ItemProcessor<BillingDetails, BillingVersion> {
	
	private BatchDao dao;
	
	public ProcessBillingVersionBuild(BatchDao dao)
	{
		this.dao=dao;
	}

	@Override
	public BillingVersion process(BillingDetails details) throws Exception {
		// TODO Auto-generated method stub

		BillingVersion vers = new BillingVersion();		

		mapBillingVersion(details, vers);

		if (!(dao.checkBillingVersionExist(vers))) {
			dao.insertBillingVersion(vers);
		}

		return vers;
	}

	private void mapBillingVersion(BillingDetails bd, BillingVersion bv) {
		
		Integer brmEmpId = dao.retrievebrmEmpId(bd.getBrm());
		bv.setBrmEmpNo(brmEmpId.toString());
		bv.setYear(bd.getBillYear());
		bv.setMonth(bd.getBillMonth());
		bv.setVersionNo(0);
		bv.setFreezeInd("N");
		bv.setLocation(bd.getOnsiteOffshore());
		
	}
	
	

}
