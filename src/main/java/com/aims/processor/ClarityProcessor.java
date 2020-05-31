package com.aims.processor;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import com.aims.bo.ClarityDetails;
import com.aims.dao.BatchDao;
import com.aims.model.ClarityMaster;

public class ClarityProcessor implements ItemProcessor<ClarityDetails, ClarityMaster> {


	private BatchDao dao;
	
	public ClarityProcessor(BatchDao dao)
	{
		this.dao=dao;
	}
	private int clarityVersion;

	
	@Override
	public ClarityMaster process(ClarityDetails details) throws Exception {

		ClarityMaster cm = new ClarityMaster();
		LocalDate currentDate = LocalDate.now();
		String month = currentDate.getMonth().toString();
		int year = currentDate.getYear();

		clarityVersion = dao.retrieveclarityVersion(month, year);

		mapClarityMaster(details, cm, clarityVersion);
		dao.insertClarityMaster(cm);

		return cm;

	}

	private void mapClarityMaster(ClarityDetails cd, ClarityMaster cm, int version) {

		cm.setVersion(version);
		cm.setTransactionClass(cd.getTransactionClass());
		cm.setCccio(cd.getCccio());
		cm.setResourceManager(cd.getResourceManager());
		cm.setTimesheetDept(cd.getTimesheetDept());
		cm.setFullName(cd.getFullName());
		cm.setResourceId(cd.getResourceId());
		cm.setOfficeId(cd.getOfficeId());
		cm.setCin(cd.getCin());
		cm.setSumOfHrs(cd.getSumOfHrs());
		cm.setAvgRate(cd.getAvgRate());
		cm.setRateWoTax(cd.getRateWoTax());
	}
	
}
