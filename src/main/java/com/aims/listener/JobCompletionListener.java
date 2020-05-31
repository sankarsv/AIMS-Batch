package com.aims.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import com.aims.dao.BatchDao;

public class JobCompletionListener extends JobExecutionListenerSupport {
	

	private BatchDao dao;
	
	public JobCompletionListener(BatchDao dao)
	{
		this.dao=dao;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
			dao.deleteHCIntermediate();
			System.out.println("HC Intermediate record deleted successfully");
		}
	}
	


}
