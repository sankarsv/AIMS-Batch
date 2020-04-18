/**
 * 
 */
package com.aims.processor;

import java.math.BigDecimal;

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
//		int empId = Integer.parseInt(emp.getEmpId());
		BigDecimal d = new BigDecimal(emp.getEmpId());
		BigDecimal d2 = d.setScale(0, BigDecimal.ROUND_HALF_UP);
		
		String jsonString = mapper.writeValueAsString(emp);
		HCIntermediate hcIntermediate = new HCIntermediate();
		System.out.println("emp id --->"+d2.intValue());
		
		hcIntermediate.setEmployeeId(d2.intValue());
		hcIntermediate.setJson(jsonString);
	    System.out.println(jsonString);
		return hcIntermediate;
	}

}
