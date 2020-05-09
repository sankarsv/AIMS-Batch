package com.aims.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee", schema = "aims")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "employee_id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;

	/*@OneToMany(targetEntity = EmployeeAllocation.class, mappedBy = "employeeId", orphanRemoval = false, fetch = FetchType.EAGER)
	private List<EmployeeAllocation> employeeAllocations;*/

	/*@OneToOne(fetch = FetchType.LAZY, mappedBy = "employeeId")
	private EmployeeAllocationPercentage employeeAllocationPercentage;*/

	@Column(name = "employee_type")
	private String employeeType;

	@Column(name = "current_location")
	private String currentLocation;

	@Column(name = "office_id")
	private String officeId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "base_branch")
	private String baseBranch;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "gender")
	private String gender;

	@Column(name = "overall_exp")
	private String overallExp;

	@Column(name = "aims_exp")
	private String aimsExp;

	@Column(name = "base_country")
	private String baseCountry;

	@Column(name = "base_dc")
	private String baseDc;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "grade")
	private String grade;

	@Column(name = "mapp_designation")
	private String mappDesignation;

	@Column(name = "senior_junior")
	private String seniorJunior;

	@Column(name = "cc_indicator")
	private String ccIndicator;

	@Column(name = "person_type")
	private String personType;

	@Column(name = "sub_person_type")
	private String subPersonType;

	@Column(name = "sob_name")
	private String sobName;

	@Column(name = "joining_date")
	private Date joiningDate;

	@Column(name = "previous_experience")
	private String previousExperience;

	@Column(name = "total_experience")
	private float totalExperience;

	@Column(name = "brm")
	private String brm;

	@Column(name = "em")
	private String em;

	@Column(name = "dm")
	private String dm;

	@Column(name = "deputebranch")
	private String deputeBranch;

	@Column(name = "deputedc")
	private String deputeDc;

	@Column(name = "employeelocation")
	private String employeeLocation;

	@Column(name = "exployeetravel")
	private String exployeeTravel;

	@Column(name = "traveltype")
	private String travelType;

	@Column(name = "employeecluster")
	private String employeeCluster;

	@Column(name = "teamrole")
	private String teamRole;

	@Column(name = "parentiou")
	private String parentIou;

	@Column(name = "childiou")
	private String childIou;

	@Column(name = "platforms")
	private String platforms;

	@Column(name = "dc")
	private String dc;

	@Column(name = "site")
	private String site;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "status")
	private String status;

	@Column(name = "expired_ind")
	private String expiredInd;

	public Employee(int employeeId, List<EmployeeAllocation> employeeAllocation, String employeeType,
			String currentLocation, String officeId, String firstName, String lastName, String baseBranch, Date dob,
			String gender, String overallExp, String aimsExp, String baseCountry, String baseDc, String categoryName,
			String grade, String mappDesignation, String seniorJunior, String ccIndicator, String personType,
			String subPersonType, String sobName, Date joiningDate, String previousExperience, float totalExperience,
			String brm, String em, String dm, String deputeBranch, String deputeDc, String employeeLocation,
			String exployeeTravel, String travelType, String employeeCluster, String teamRole, String parentIou,
			String childIou, String platforms, String dc, String site, Date createdAt, String status,
			String expiredInd) {
		super();
		//this.employeeAllocations = employeeAllocation;
		this.employeeId = employeeId;
		this.employeeType = employeeType;
		this.currentLocation = currentLocation;
		this.officeId = officeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.baseBranch = baseBranch;
		this.dob = dob;
		this.gender = gender;
		this.overallExp = overallExp;
		this.aimsExp = aimsExp;
		this.baseCountry = baseCountry;
		this.baseDc = baseDc;
		this.categoryName = categoryName;
		this.grade = grade;
		this.mappDesignation = mappDesignation;
		this.seniorJunior = seniorJunior;
		this.ccIndicator = ccIndicator;
		this.personType = personType;
		this.subPersonType = subPersonType;
		this.sobName = sobName;
		this.joiningDate = joiningDate;
		this.previousExperience = previousExperience;
		this.totalExperience = totalExperience;
		this.brm = brm;
		this.em = em;
		this.dm = dm;
		this.deputeBranch = deputeBranch;
		this.deputeDc = deputeDc;
		this.employeeLocation = employeeLocation;
		this.exployeeTravel = exployeeTravel;
		this.travelType = travelType;
		this.employeeCluster = employeeCluster;
		this.teamRole = teamRole;
		this.parentIou = parentIou;
		this.childIou = childIou;
		this.platforms = platforms;
		this.dc = dc;
		this.site = site;
		this.createdAt = createdAt;
		this.status = status;
		this.expiredInd = expiredInd;

	}

	public Employee() {
		super();
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	/*public List<EmployeeAllocation> getEmployeeAllocations() {
		return employeeAllocations;
	}

	public void setEmployeeAllocations(List<EmployeeAllocation> employeeAllocations) {
		this.employeeAllocations = employeeAllocations;
	}*/

	/*public EmployeeAllocationPercentage getEmployeeAllocationPercentage() {
		return employeeAllocationPercentage;
	}

	public void setEmployeeAllocationPercentage(EmployeeAllocationPercentage employeeAllocationPercentage) {
		this.employeeAllocationPercentage = employeeAllocationPercentage;
	}*/

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBaseBranch() {
		return baseBranch;
	}

	public void setBaseBranch(String baseBranch) {
		this.baseBranch = baseBranch;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOverallExp() {
		return overallExp;
	}

	public void setOverallExp(String overallExp) {
		this.overallExp = overallExp;
	}

	public String getAimsExp() {
		return aimsExp;
	}

	public void setAimsExp(String aimsExp) {
		this.aimsExp = aimsExp;
	}

	public String getBaseCountry() {
		return baseCountry;
	}

	public void setBaseCountry(String baseCountry) {
		this.baseCountry = baseCountry;
	}

	public String getBaseDc() {
		return baseDc;
	}

	public void setBaseDc(String baseDc) {
		this.baseDc = baseDc;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMappDesignation() {
		return mappDesignation;
	}

	public void setMappDesignation(String mappDesignation) {
		this.mappDesignation = mappDesignation;
	}

	public String getSeniorJunior() {
		return seniorJunior;
	}

	public void setSeniorJunior(String seniorJunior) {
		this.seniorJunior = seniorJunior;
	}

	public String getCcIndicator() {
		return ccIndicator;
	}

	public void setCcIndicator(String ccIndicator) {
		this.ccIndicator = ccIndicator;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getSubPersonType() {
		return subPersonType;
	}

	public void setSubPersonType(String subPersonType) {
		this.subPersonType = subPersonType;
	}

	public String getSobName() {
		return sobName;
	}

	public void setSobName(String sobName) {
		this.sobName = sobName;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getPreviousExperience() {
		return previousExperience;
	}

	public void setPreviousExperience(String previousExperience) {
		this.previousExperience = previousExperience;
	}

	public float getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(float totalExperience) {
		this.totalExperience = totalExperience;
	}

	public String getBrm() {
		return brm;
	}

	public void setBrm(String brm) {
		this.brm = brm;
	}

	public String getEm() {
		return em;
	}

	public void setEm(String em) {
		this.em = em;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getDeputeBranch() {
		return deputeBranch;
	}

	public void setDeputeBranch(String deputeBranch) {
		this.deputeBranch = deputeBranch;
	}

	public String getDeputeDc() {
		return deputeDc;
	}

	public void setDeputeDc(String deputeDc) {
		this.deputeDc = deputeDc;
	}

	public String getEmployeeLocation() {
		return employeeLocation;
	}

	public void setEmployeeLocation(String employeeLocation) {
		this.employeeLocation = employeeLocation;
	}

	public String getExployeeTravel() {
		return exployeeTravel;
	}

	public void setExployeeTravel(String exployeeTravel) {
		this.exployeeTravel = exployeeTravel;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getEmployeeCluster() {
		return employeeCluster;
	}

	public void setEmployeeCluster(String employeeCluster) {
		this.employeeCluster = employeeCluster;
	}

	public String getTeamRole() {
		return teamRole;
	}

	public void setTeamRole(String teamRole) {
		this.teamRole = teamRole;
	}

	public String getParentIou() {
		return parentIou;
	}

	public void setParentIou(String parentIou) {
		this.parentIou = parentIou;
	}

	public String getChildIou() {
		return childIou;
	}

	public void setChildIou(String childIou) {
		this.childIou = childIou;
	}

	public String getPlatforms() {
		return platforms;
	}

	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExpiredInd() {
		return expiredInd;
	}

	public void setExpiredInd(String expiredInd) {
		this.expiredInd = expiredInd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeAllocations=" 
				+ ", employeeAllocationPercentage="  + ", employeeType=" + employeeType
				+ ", currentLocation=" + currentLocation + ", officeId=" + officeId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", baseBranch=" + baseBranch + ", dob=" + dob + ", gender=" + gender
				+ ", overallExp=" + overallExp + ", aimsExp=" + aimsExp + ", baseCountry=" + baseCountry + ", baseDc="
				+ baseDc + ", categoryName=" + categoryName + ", grade=" + grade + ", mappDesignation="
				+ mappDesignation + ", seniorJunior=" + seniorJunior + ", ccIndicator=" + ccIndicator + ", personType="
				+ personType + ", subPersonType=" + subPersonType + ", sobName=" + sobName + ", joiningDate="
				+ joiningDate + ", previousExperience=" + previousExperience + ", totalExperience=" + totalExperience
				+ ", brm=" + brm + ", em=" + em + ", dm=" + dm + ", deputeBranch=" + deputeBranch + ", deputeDc="
				+ deputeDc + ", employeeLocation=" + employeeLocation + ", exployeeTravel=" + exployeeTravel
				+ ", travelType=" + travelType + ", employeeCluster=" + employeeCluster + ", teamRole=" + teamRole
				+ ", parentIou=" + parentIou + ", childIou=" + childIou + ", platforms=" + platforms + ", dc=" + dc
				+ ", site=" + site + ", createdAt=" + createdAt + ", status=" + status + ", expiredInd=" + expiredInd
				+ "]";
	}

}