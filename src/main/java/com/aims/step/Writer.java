package com.aims.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.aims.model.BillingMaster;
import com.aims.model.BillingVersion;

public class Writer implements ItemWriter<BillingVersion> {

	@Override
	public void write(List<? extends BillingVersion> messages) throws Exception {
		
		
	}
	

}

