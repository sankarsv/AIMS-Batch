package com.aims.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.aims.model.HCIntermediate;

public class Writer implements ItemWriter<HCIntermediate> {

	@Override
	public void write(List<? extends HCIntermediate> messages) throws Exception {
		
		
	}

}