package com.aims.processor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.batch.item.ItemProcessor;

import com.aims.model.HCIntermediate;

public class ProcessSetFileResource implements ItemProcessor<HCIntermediate, String> {
	
	@Override
	public String process(HCIntermediate hcin) throws Exception {

		File file = new File("src/main/resources/Master_Feed.xlsx");

		OutputStream os = new FileOutputStream(file);
		os.write(hcin.getFiledata());
		os.close();

		return "Successful";

	}

}
