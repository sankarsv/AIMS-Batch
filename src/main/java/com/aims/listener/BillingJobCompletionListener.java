package com.aims.listener;

import javax.sql.DataSource;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import com.aims.dao.BatchDao;

public class BillingJobCompletionListener extends JobExecutionListenerSupport {
	
	private DataSource datasource = null;

	private BatchDao dao = null;

	public BillingJobCompletionListener(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("Billing batch job completed successfully");
			getDao(datasource).deleteBRIntermediate();
			System.out.println("BR Intermediate record deleted successfully");
		}
	}
	
	private BatchDao getDao(DataSource datasource) {
		if (this.dao == null) {
			dao = new BatchDao(datasource);
		}

		return dao;
	}

}
