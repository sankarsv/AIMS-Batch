package com.aims.processor;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemProcessor;

import com.aims.bo.BillingDetails;
import com.aims.dao.BatchDao;
import com.aims.model.BillingMaster;

public class ProcessBillingMasterBuild implements ItemProcessor<BillingDetails, BillingMaster> {

	private int billingVersion;

	public ProcessBillingMasterBuild(DataSource datasource) {
		this.billingVersion = new BatchDao(datasource).retrieveBillingVersion();
	}

	@Override
	public BillingMaster process(BillingDetails details) throws Exception {
		// TODO Auto-generated method stub

		BillingMaster mas = new BillingMaster();

		mapBillingVersion(details, mas);

		return mas;
	}

	private void mapBillingVersion(BillingDetails bd, BillingMaster bm) {

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

	}

}
