/**
 * 
 */
package com.aims.processor;

import org.springframework.batch.item.ItemProcessor;

import com.aims.bo.Employee;
import com.aims.model.HCIntermediate;

/**
 * @author ShanmugapriyaJ
 *
 */
public class ProcessorConvertToJson  implements ItemProcessor<Employee, HCIntermediate> {

	@Override
	public HCIntermediate process(Employee emp) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
