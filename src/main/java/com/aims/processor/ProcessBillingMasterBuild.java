package com.aims.processor;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.aims.bo.BillingDetails;
import com.aims.dao.BatchDao;
import com.aims.model.BillingMaster;

public class ProcessBillingMasterBuild implements ItemProcessor<BillingDetails, BillingMaster> {


	private DataSource datasource = null;;

	private BatchDao dao = null;

	public ProcessBillingMasterBuild(DataSource datasource) {
		this.datasource = datasource;
			}

	@Override
	public BillingMaster process(BillingDetails details) throws Exception {
		// TODO Auto-generated method stub

		BillingMaster mas = new BillingMaster();
		
		int billingVersion = getDao(datasource).retrieveBillingVersion(details);

		mapBillingMaster(details, mas,billingVersion);

		KeyHolder holder = new GeneratedKeyHolder();

		if (getDao(datasource).upDateBillMaster(holder, mas) == 0 ){
		
			getDao(datasource).insertBillingDetails(holder, mas);
		}

		if (getDao(datasource).upDateBillRate(holder, details) == 0) {

			getDao(datasource).insertBillRate(holder, details, billingVersion);
		}

		return mas;
	}

	private void mapBillingMaster(BillingDetails bd, BillingMaster bm, int billingVersion) {
		
		
		bm.setVersionNo(billingVersion);
		bm.setEmpId(bd.getEmpId());
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
		bm.setEmpName(bd.getEmpName());
		bm.setBrmName(bd.getBrm());
		bm.setOfficeId(bd.getOfficeId());

	}

	private BatchDao getDao(DataSource datasource) {
		if (this.dao == null) {
			dao = new BatchDao(datasource);
		}

		return dao;
	}

}
