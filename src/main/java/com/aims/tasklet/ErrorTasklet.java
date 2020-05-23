package com.aims.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ErrorTasklet  implements Tasklet {
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {

		System.out.println("An error has occured during batch step" );
		return RepeatStatus.FINISHED;
	}

}
