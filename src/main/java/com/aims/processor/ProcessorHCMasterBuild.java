/**
 * 
 */
package com.aims.processor;

import org.springframework.batch.item.ItemProcessor;

import com.aims.bo.Employee;
import com.aims.model.HCDetails;

/**
 * @author ShanmugapriyaJ
 *
 */
public class ProcessorHCMasterBuild  implements ItemProcessor<Employee, HCDetails> {

	@Override
	public HCDetails process(Employee emp) throws Exception {
		// TODO Auto-generated method stub

		HCDetails hcDetails = new HCDetails();
		
		mapHcMaster(emp,hcDetails);
		
	
		return hcDetails;
	}
	
	private void mapHcMaster(Employee emp,HCDetails hcd)
	{
		hcd.setVersionNo(Integer.parseInt(emp.getVersionNo()));
		hcd.setEmpNo(Integer.parseInt(emp.getEmpId()));
		hcd.setWorkGeography(emp.getWorkGeography());
		hcd.setWorkLocation(emp.getWorkLocation());
		hcd.setWorkCountry(emp.getWorkCountry());
		hcd.setClientGeography(emp.getClientGeography());
		hcd.setClientCountry(emp.getClientCountry());
		hcd.setIp(emp.getIP());
		hcd.setSp(emp.getSP());
		hcd.setSubSP(emp.getSubSP());
		hcd.setCustomer(emp.getCustomer());
		hcd.setGroupCustomer(emp.getGroupCustomer());
		
		hcd.setRm(emp.getRm());
		hcd.setBrm(emp.getBrmName());
		hcd.setGl(emp.getGlName());
		hcd.setAmID(emp.getAmNumber());
		hcd.setAm(emp.getAMName());
		hcd.setProjectID(emp.getProjectNumber());
		hcd.setPl(emp.getPl());
		hcd.setProjectName(emp.getProjectName());
		hcd.setProjectLoc(emp.getProjectLocation());
		hcd.setProjectType(emp.getProjectType());
		hcd.setTurnkey(emp.getTurnkeyFlag());
		hcd.setSnowcategory(emp.getSwonCategory());
		hcd.setCluster(emp.getProjectCluster());
		hcd.setIou(emp.getIOU());
		
		hcd.setSubiou(emp.getSubIOU());
		hcd.setEmpName(emp.getEmployeeName());
		hcd.setBrm1(emp.getBrmEmName());
		hcd.setDm(emp.getDm());
		hcd.setEmployeeType(emp.getEmployeeType());
		hcd.setDoj(emp.getDoj());
		hcd.setDeputeDC(emp.getDeputeDC());
		hcd.setDeupteBranch(emp.getDeputeBranch());
		hcd.setEmployeeLocation(emp.getEmployeeLocation());
		hcd.setBaseCountry(emp.getEmployeeBaseCountry());
		
		hcd.setBaseBranch(emp.getBaseBranch());
		hcd.setBaseDC(emp.getBaseDC());
		hcd.setTravelCountry(emp.getEmployeeTravelCountry());
		hcd.setTravelType(emp.getTravelType());
		hcd.setDesignation(emp.getDesignation());
		hcd.setGrade(emp.getGrade());
		hcd.setMapDesignation(emp.getMappDesignation());
		hcd.setSeniorjunior(emp.getSeniorJunior());
		hcd.setCcInd(emp.getCCNonCC());
		hcd.setPersonType(emp.getPersonType());
		hcd.setSubPersonType(emp.getSubPersonType());
		hcd.setSdbname(emp.getSobName());
		hcd.setExperience(emp.getTCSExp());
		hcd.setTotExp(emp.getTotalExp());
		hcd.setAllocStart(emp.getAllocationStartDate());
		hcd.setAllocEnd(emp.getAllocationEndDate());
		hcd.setPercAlloc(emp.getPercentageAllocation());
		hcd.setEmployeecluster(emp.getEmployeeCluster());
		hcd.setParentiou(emp.getParentIOU());
		hcd.setChildiou(emp.getChildIOU());
		hcd.setTeamrole(emp.getTeamRole());
		
		hcd.setPlatform(emp.getPlatforms());
		hcd.setDc(emp.getDc());
		hcd.setSite(emp.getSite());
		
		/*System.out.println("Value of hcdetails " +hcd.getAllocEnd() + "--" +hcd.getAllocStart() + "--"
				+ hcd.getAm() + "--" +hcd.getAmID()+ "--" +hcd.getBaseBranch()+ "--" +hcd.getBaseCountry()+ "--" +hcd.getBaseDC()+ "--" +hcd.getBrm()
				+ "--" +hcd.getBrm1()+ "--" +hcd.getCcInd()+ "--" +hcd.getChildiou()+ "--" +hcd.getClientCountry()+ "--" +hcd.getClientGeography()
				+ "--" +hcd.getCluster()+ "--" +hcd.getCustomer()+ "--" +hcd.getDc()+ "--" +hcd.getDeputeDC()+ "--" +hcd.getDesignation()+ "--" +hcd.getDeupteBranch()
				+ "--" +hcd.getDm()+ "--" +hcd.getDoj()+ "--" +hcd.getEmployeecluster()+ "--" +hcd.getEmployeeLocation()+ "--" +hcd.getEmployeetype()+ "--" +hcd.getEmployeeType()
				+ "--" +hcd.getEmpName()+ "--" +hcd.getEmpNo()+ "--" +hcd.getExperience()+ "--" +hcd.getGl()+ "--" +hcd.getGrade()+ "--" +hcd.getGroupCustomer()+ "--" +hcd.getIou()
				+ "--" +hcd.getIp()+ "--" +hcd.getMapDesignation()+ "--" +hcd.getParentiou()+ "--" +hcd.getPercAlloc()+ "--" +hcd.getPersonType()+ "--" +hcd.getPl()
				+ "--" +hcd.getPlatform()+ "--" +hcd.getProjectID()+ "--" +hcd.getProjectLoc()+ "--" +hcd.getProjectName()+ "--" +hcd.getProjectType()+ "--" +hcd.getRm()+ "--" +hcd.getSdbname()
				+ "--" +hcd.getSeniorjunior()+ "--" +hcd.getSite()+ "--" +hcd.getSnowcategory()+ "--" +hcd.getSp()+ "--" +hcd.getSubiou()+ "--" +hcd.getSubPersonType()+ "--" +hcd.getSubSP()
				+ "--" +hcd.getTeamrole()+ "--" +hcd.getTotExp()+ "--" +hcd.getTravelCountry()+ "--" +hcd.getTravelType()+ "--" +hcd.getTurnkey()+ "--" +hcd.getWorkCountry()
				+ "--" +hcd.getWorkGeography()+ "--" +hcd.getWorkLocation()+ "--");*/
	}

}
