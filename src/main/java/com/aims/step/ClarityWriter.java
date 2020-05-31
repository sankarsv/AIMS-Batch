package com.aims.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.aims.model.ClarityMaster;

public class ClarityWriter implements ItemWriter<ClarityMaster> {

	@Override
	public void write(List<? extends ClarityMaster> messages) throws Exception {
		
		
	}
}
