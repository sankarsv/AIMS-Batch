package com.aims.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.aims.bo.BillingDetails;
import com.aims.dao.BatchDao;
import com.aims.model.BillingMaster;

public class ProcessBillingMasterBuild implements ItemProcessor<BillingDetails, BillingMaster> {

	private BatchDao dao;
	
	public ProcessBillingMasterBuild(BatchDao dao)
	{
		this.dao=dao;
	}

	
	@Override
	public BillingMaster process(BillingDetails details) throws Exception {
		// TODO Auto-generated method stub
		
		BillingMaster mas = new BillingMaster();
		
		if(details.getBrm()!=null) {
		Integer brmEmpId = dao.retrievebrmEmpId(details.getBrm());
		
		int billingVersion = dao.retrieveBillingVersion(details,brmEmpId.toString());

		mapBillingMaster(details, mas,billingVersion);

		KeyHolder holder = new GeneratedKeyHolder();
		
		int retVal = dao.saveEmployeeOfficeId(holder,details.getOfficeId(),details.getEmpId());

		if (dao.upDateBillMaster(holder, mas) == 0 ){
		
			dao.insertBillingDetails(holder, mas);
		}

		if (dao.upDateBillRate(holder, details) == 0) {

			dao.insertBillRate(holder, details, billingVersion);
		}
		}

		return mas;
	}

	private void mapBillingMaster(BillingDetails bd, BillingMaster bm, int billingVersion) {
		
		
		bm.setVersionNo(billingVersion);
		bm.setEmpId(bd.getEmpId());
		bm.setProjectName(bd.getProjectName());
		bm.setDmName(bd.getDmName());
		bm.setOnsiteOffshore(bd.getOnsiteOffshore());
		bm.setWonNumber(bd.getWonNumber());
		bm.setSto(bd.getSto());
		bm.setBillablehrs(bd.getBillablehrs());
		bm.setBillableDays(bd.getBillableDays());
		bm.setEffort(bd.getEffort());
		bm.setExtraHours(bd.getExtraHours());
		bm.setExtraBilling(bd.getExtraBilling());
		bm.setBillableAmount(bd.getBillableAmount());
		bm.setRemarks1(bd.getRemarks1());
		bm.setRemarks2(bd.getRemarks2());
		/*bm.setEmpName(bd.getEmpName());
		bm.setBrmName(bd.getBrm());
		bm.setOfficeId(bd.getOfficeId());*/

	}

	

}
