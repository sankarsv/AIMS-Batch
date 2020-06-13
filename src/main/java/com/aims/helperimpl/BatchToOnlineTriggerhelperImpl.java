package com.aims.helperimpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aims.dao.BatchDao;
import com.aims.helperinterface.BatchToOnlineTriggerhelperInterface;
import com.aims.model.AllocationPk;
import com.aims.model.Employee;
import com.aims.model.EmployeeAllocation;
import com.aims.model.HCDetails;
import com.aims.model.Project;
import com.aims.repository.HCMAsterRepository;
import com.aims.service.EmployeeAllocationService;
import com.aims.service.EmployeeService;
import com.aims.service.ProjectService;

@Component
public class BatchToOnlineTriggerhelperImpl implements BatchToOnlineTriggerhelperInterface{

	@Autowired
	private HCMAsterRepository hcMAsterRepository;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private EmployeeAllocationService employeeAllocationService;
	
	@Autowired
	DataSource datasource;
	
	private BatchDao dao;

	@Override
	public void populateOnlineData() {
		System.out.println("***Enter in to getHcMasterdata***");

		List<HCDetails> hcMasterList = new ArrayList<>();
		List<Employee> employeePresentList = new ArrayList<>();
		List<Project> projectPresentList = new ArrayList<>();
		List<EmployeeAllocation> empAllocationPresentList = new ArrayList<>();

		List<Integer> dbEmployeeExistList = new ArrayList<>();
		List<Integer> dbProjectExistList = new ArrayList<>();
		List<Integer> dbEmployeeAllocationExistList = new ArrayList<>();

		List<Integer> employeeXLExistList = new ArrayList<>();
		List<Integer> projectXLExistList = new ArrayList<>();

		List<Employee> employeeList = new ArrayList<>();
		List<Project> projectList = new ArrayList<>();
		List<EmployeeAllocation> employeeAllocationList = new ArrayList<>();

		hcMasterList = hcMAsterRepository.findAll();
		employeePresentList = employeeService.getAllEmployees();
		projectPresentList = projectService.getAllProjects();
		empAllocationPresentList = employeeAllocationService.getAllEmployeeAllocations();

		System.out.println("***End in getHcMasterdata***" + hcMasterList);

		if (hcMasterList != null) {

			if (employeePresentList != null) {

				for (Employee EmployeeDB : employeePresentList) {

					dbEmployeeExistList.add(EmployeeDB.getEmployeeId());

				}

			}

			if (empAllocationPresentList != null) {

				for (EmployeeAllocation employeeAllocationDB : empAllocationPresentList) {

					dbEmployeeAllocationExistList.add(employeeAllocationDB.getEmployeeId());

				}

			}

			if (projectPresentList != null) {

				for (Project projectDB : projectPresentList) {

					dbProjectExistList.add(projectDB.getProjectId());

				}

			}

			for (HCDetails tempXLPrepare : hcMasterList) {

				employeeXLExistList.add(tempXLPrepare.getEmpNo());

				int project_id = Integer.parseInt(tempXLPrepare.getProjectID());
				projectXLExistList.add(project_id);

			}

			dbEmployeeExistList.removeAll(employeeXLExistList);
			System.out.println("Db Employee present but missing in XL-->" + dbEmployeeExistList);

			dbProjectExistList.removeAll(projectXLExistList);
			System.out.println("Db project  present but missing in XL-->" + dbProjectExistList);

			dbEmployeeAllocationExistList.removeAll(employeeXLExistList);
			System.out.println("Db EmployeeAllocation present but missing in XL-->" + dbEmployeeAllocationExistList);

			if (dbEmployeeAllocationExistList != null) {

				for (int i = 0; i < dbEmployeeAllocationExistList.size(); i++) {
					System.out.print(dbEmployeeAllocationExistList.get(i) + " ");
					Integer empID = dbEmployeeAllocationExistList.get(i);
					List<EmployeeAllocation> employeeAllocationfindByEmpIdList = employeeAllocationService
							.getByEmployeeId(empID);
					for (int M = 0; M < employeeAllocationfindByEmpIdList.size(); M++) {
						System.out.print(employeeAllocationfindByEmpIdList.get(M) + " ");
						EmployeeAllocation employeeAllocationExpried = employeeAllocationfindByEmpIdList.get(M);
						LocalDate today = LocalDate.now();
						if (employeeAllocationExpried.getEndDate() == null) {
							employeeAllocationExpried.setEndDate(today);
						}
						employeeAllocationList.add(employeeAllocationExpried);

					}

				}

			}

			if (dbEmployeeExistList != null) {

				for (int k = 0; k < dbEmployeeExistList.size(); k++) {
					System.out.print(dbEmployeeExistList.get(k) + " ");
					Integer empID = dbEmployeeExistList.get(k);
					Optional<Employee> updateEmp1 = employeeService.getById(empID);
					Employee updatedExpried = updateEmp1.get();
					updatedExpried.setExpiredInd("Y");
					employeeList.add(updatedExpried);

				}

			}

			if (dbProjectExistList != null) {

				for (int j = 0; j < dbProjectExistList.size(); j++) {
					System.out.print(dbProjectExistList.get(j) + " ");
					Integer projectID = dbProjectExistList.get(j);
					Optional<Project> updateProject = projectService.getById(projectID);
					Project projectExpried = updateProject.get();
					projectExpried.setExpiredInd("Y");
					projectList.add(projectExpried);

				}

			}

			for (HCDetails tempHCDtl : hcMasterList) {

				System.out.println("Printing emp no -->" + tempHCDtl.getEmpNo());

				Employee employee = new Employee();
				// employee.setEmployeeId(tempHCDtl.getHcMasterIdentity().getEmpNo());
				employee.setEmployeeId(tempHCDtl.getEmpNo());
				employee.setEmployeeType(tempHCDtl.getEmployeetype());
				employee.setCurrentLocation(tempHCDtl.getEmployeeLocation());
				// employee.setOfficeId(tempHCDtl.);
				employee.setFirstName(tempHCDtl.getEmpName());
				// employee.setLastName(tempHCDtl.);
				employee.setBaseBranch(tempHCDtl.getBaseBranch());
				// employee.setDob(tempHCDtl);
				// employee.setGender(tempHCDtl.);
				employee.setOverallExp(tempHCDtl.getTotExp());
				employee.setAimsExp(tempHCDtl.getExperience());
				employee.setBaseCountry(tempHCDtl.getBaseCountry());
				employee.setBaseDc(tempHCDtl.getBaseDC());
				// employee.setCategoryName(tempHCDtl);
				employee.setGrade(tempHCDtl.getGrade());
				employee.setMappDesignation(tempHCDtl.getMapDesignation());
				employee.setSeniorJunior(tempHCDtl.getSeniorjunior());
				employee.setCcIndicator(tempHCDtl.getCcInd());
				employee.setPersonType(tempHCDtl.getPersonType());
				employee.setSubPersonType(tempHCDtl.getSubPersonType());
				employee.setSobName(tempHCDtl.getSdbname());
				// employee.setJoiningDate(tempHCDtl.getDoj()); - Type conversion issue
				// employee.setPreviousExperience(tempHCDtl.);
				// employee.setTotalExperience(tempHCDtl.getTotExp()); - Type conversion issue
				Integer brmId = getDao().retrievebrmEmpId(tempHCDtl.getBrm());
				employee.setBrm(brmId.toString());
				// employee.setEm(tempHCDtl.);
				
				Integer dmId = getDao().retrieveDmEmpId(tempHCDtl.getDm());
				employee.setDm(dmId.toString());
				employee.setDeputeBranch(tempHCDtl.getDeupteBranch());
				employee.setDeputeDc(tempHCDtl.getDeputeDC());
				employee.setEmployeeLocation(tempHCDtl.getEmployeeLocation());
				employee.setExployeeTravel(tempHCDtl.getTravelCountry());
				employee.setTravelType(tempHCDtl.getTravelType());
				employee.setEmployeeCluster(tempHCDtl.getEmployeecluster());

				employee.setTeamRole(tempHCDtl.getTeamrole());
				employee.setParentIou(tempHCDtl.getParentiou());
				employee.setChildIou(tempHCDtl.getChildiou());
				employee.setPlatforms(tempHCDtl.getPlatform());
				employee.setDc(tempHCDtl.getDc());
				employee.setSite(tempHCDtl.getSite());
				// employee.setCreatedAt(tempHCDtl.get); // we may need to create current date
				// of record creation date and time
				// employee.setStatus(tempHCDtl.);//Need to check
				// employee.setExpiredInd(expiredInd); //Need to think the logic

				System.out.println("***Printing Employee***" + employee.toString());

				employeeList.add(employee);

				// Project Bean preparation

				// Check if project exists in DB

				System.out.println("project flag" + projectService.getById(Integer.parseInt(tempHCDtl.getProjectID())));

				boolean projectcheckFlag = projectService.getById(Integer.parseInt(tempHCDtl.getProjectID()))
						.isPresent();

				Project project = new Project();

				int project_id = Integer.parseInt(tempHCDtl.getProjectID());
				System.out.println("Printing Project ID -->" + project_id);
				project.setProjectId(project_id);
				project.setProjectName(tempHCDtl.getProjectName());
				project.setProjectLocation(tempHCDtl.getProjectLoc());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate today = LocalDate.now();
				System.out.println(formatter.format(today));
				project.setProjectChangeDate(today); // Need to revist
				project.setWorkCountry(tempHCDtl.getWorkCountry());
				project.setWorkLocation(tempHCDtl.getWorkLocation());
				project.setClientGeography(tempHCDtl.getClientGeography());
				project.setCustomer(tempHCDtl.getCustomer());
				project.setGroupCustomer(tempHCDtl.getGroupCustomer());
				project.setIp(tempHCDtl.getIp());
				project.setSp(tempHCDtl.getSp());
				project.setSubsp(tempHCDtl.getSubSP());
				project.setBrm(brmId.toString());
				project.setGl(tempHCDtl.getGl());
				project.setAmid(tempHCDtl.getAmID());
				project.setAm(tempHCDtl.getAm());
				project.setPl(tempHCDtl.getPl());
				project.setGroup_Customer(tempHCDtl.getGroupCustomer());
				project.setProjectHash(project_id);
				project.setProjectLocationWrtIndia(tempHCDtl.getProjectLoc());
				project.setProjectType(tempHCDtl.getProjectType());
				project.setPureTurnkeyFlag(tempHCDtl.getTurnkey());
				project.setSwonCategory(tempHCDtl.getSnowcategory());
				project.setProjectCluster(tempHCDtl.getCluster());
				project.setIou(tempHCDtl.getIou());
				project.setSubIou(tempHCDtl.getSubiou());
				project.setParentIou(tempHCDtl.getParentiou());
				project.setChildIou(tempHCDtl.getChildiou());
				project.setBfsCluster(tempHCDtl.getCluster());
				project.setId(project_id); // Time being seeting project ID though Anand said to ignore it.
				project.setWorkGeography(tempHCDtl.getWorkGeography());
				// project.setExpiredInd(); Need to think the logic

				System.out.println("***Printing Project details records ***" + project.toString());

				projectList.add(project);

				// Allocation Bean preparation
				EmployeeAllocation empAllocation = new EmployeeAllocation();
				AllocationPk allocationNewPk = new AllocationPk();
				//allocationNewPk.setAllocationId(0);
				System.out
						.println("***Printing empAllocation - EmpNo ***" + tempHCDtl.getEmpNo());
				empAllocation.setEmployeeId(tempHCDtl.getEmpNo());
				empAllocation.setProjectId(project_id);
				DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d-MMM-yy");

				if (tempHCDtl.getAllocStart() != null) {
					System.out.println("Printing allocation start Date -->" + tempHCDtl.getAllocStart());
					String allocationStdate = tempHCDtl.getAllocStart();
					empAllocation.setStartDate(LocalDate.parse(allocationStdate, formatter2));

				}

				//empAllocation.setAllocationNew_pk(allocationNewPk);

				empAllocation.setWonId(project_id);

				String changeDate = LocalDate.now().toString();
				System.out.println("Printing changeDate in Employee Allocation Block-->" + changeDate);
				empAllocation.setProjectChangeDate(LocalDate.now());
				// empAllocation.setPortfolioId();

				if (tempHCDtl.getAllocStart() != null) {
					System.out.println("Printing allocation start Date -->" + tempHCDtl.getAllocStart());
					String allocationStdate = tempHCDtl.getAllocStart();
					empAllocation.setStartDate(LocalDate.parse(allocationStdate, formatter2));
					// empAllocation.setStartDate(LocalDate.parse(allocationStdate, formatter2));
				}

				if (tempHCDtl.getAllocEnd() != null) {
					System.out.println("Printing allocation start Date -->" + tempHCDtl.getAllocStart());
					String allocationEndate = tempHCDtl.getAllocEnd();
					empAllocation.setEndDate(LocalDate.parse(allocationEndate, formatter2));
				}

				empAllocation.setTravelType(tempHCDtl.getTravelType());
				float fValue = Float.valueOf(tempHCDtl.getPercAlloc());
				empAllocation.setPercentageAllocation(fValue);

				System.out.println("***Printing Employee Allocation details records ***" + empAllocation.toString());

				employeeAllocationList.add(empAllocation);

			}

			List<Employee> employees = employeeService.addEmployees(employeeList);
			System.out.println("Stored Employees retruned List -->" + employees);
			List<Project> Projects = projectService.addProjects(projectList);
			System.out.println("Stored Projects retruned List -->" + Projects);
			List<EmployeeAllocation> employeeAllocations = employeeAllocationService
					.addEmployeeAllocations(employeeAllocationList);
			System.out.println("Stored employeeAllocations retruned List -->" + employeeAllocations);

			

		}

	}
	
	private BatchDao getDao()
	{
		if(dao==null) {
			dao = new BatchDao(datasource); 
		}
		
		return dao;
	}

}
