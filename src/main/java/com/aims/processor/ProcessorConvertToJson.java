/**
 * 
 */
package com.aims.processor;

import org.springframework.batch.item.ItemProcessor;

import com.aims.bo.Employee;
import com.aims.model.HCIntermediate;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ShanmugapriyaJ
 *
 */
public class ProcessorConvertToJson  implements ItemProcessor<Employee, HCIntermediate> {

	@Override
	public HCIntermediate process(Employee emp) throws Exception {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(emp);
	    System.out.println(jsonString);
		return null;
	}

}
