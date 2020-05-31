package com.aims.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.aims.dao.BatchDao;

public class DbWriteTasklet implements Tasklet {
	
	private BatchDao dao;
	
	public DbWriteTasklet(BatchDao dao)
	{
		this.dao=dao;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub

		KeyHolder holder = new GeneratedKeyHolder();

		dao.updateHCVersion(holder);

		dao.insertHCVersion(holder);

		chunkContext.getStepContext().getStepExecution().getExecutionContext().put("versionNo",
				dao.retrieveHCVersion());

		return RepeatStatus.FINISHED;
	}

}
