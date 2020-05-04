package com.aims.tasklet;

import javax.sql.DataSource;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.aims.dao.BatchDao;

public class DbWriteTasklet implements Tasklet {

	private DataSource datasource = null;;

	private BatchDao dao = null;

	public DbWriteTasklet(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub

		KeyHolder holder = new GeneratedKeyHolder();

		getDao(datasource).updateHCVersion(holder);

		getDao(datasource).insertHCVersion(holder);

		chunkContext.getStepContext().getStepExecution().getExecutionContext().put("versionNo",
				getDao(datasource).retrieveHCVersion());

		return RepeatStatus.FINISHED;
	}

	private BatchDao getDao(DataSource datasource) {
		if (this.dao == null) {
			dao = new BatchDao(datasource);
		}

		return dao;
	}

}
