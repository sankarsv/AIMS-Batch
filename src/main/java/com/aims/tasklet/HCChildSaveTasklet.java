package com.aims.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.aims.helperinterface.BatchToOnlineTriggerhelperInterface;

public class HCChildSaveTasklet implements Tasklet {
	
	
	BatchToOnlineTriggerhelperInterface batchtoOnlineInt;
	
	public HCChildSaveTasklet (BatchToOnlineTriggerhelperInterface batchtoOnlineInt)
	{
		this.batchtoOnlineInt=batchtoOnlineInt;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		batchtoOnlineInt.populateOnlineData();
		return RepeatStatus.FINISHED;
	}
	

}
