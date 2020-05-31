package com.aims.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import com.aims.dao.BatchDao;

public class ClarityJobCompletionListener extends JobExecutionListenerSupport {	

	private BatchDao dao;
	
	public ClarityJobCompletionListener(BatchDao dao)
	{
		this.dao=dao;
	}
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("Clarity batch job completed successfully");
			dao.deleteClarityIntermediate();
			System.out.println("Clarity Intermediate record deleted successfully");
		}
	}
	
	
	

}
