package com.aims.tasklet;

import java.time.LocalDate;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.aims.dao.BatchDao;

public class ClarityVersionUpdateTasklet implements Tasklet {

	private BatchDao dao;

	public ClarityVersionUpdateTasklet(BatchDao dao) {
		this.dao = dao;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {

		try {
			if (dao.getClarityInter() != null) {
				LocalDate currentDate = LocalDate.now();
				String month = currentDate.minusMonths(1).getMonth().toString();
				int year = currentDate.getYear();
				KeyHolder holder = new GeneratedKeyHolder();
				dao.updateClarityVersion(holder, month, year);

			}
		} catch (Exception ex) {
			System.out.println("Error occured during clarityintermediate retrieve");
		}
		return RepeatStatus.FINISHED;
	}

}
