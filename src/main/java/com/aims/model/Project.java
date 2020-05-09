package com.aims.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PROJECT", schema = "aims")
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "project_id")
	@NotNull
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "project_location")
	private String projectLocation;

	@Column(name = "project_change_date")
	@NotNull
	private LocalDate projectChangeDate;

	@Column(name = "work_geogrphy")
	private String workGeogrphy;

	@Column(name = "work_country")
	private String workCountry;

	@Column(name = "work_location")
	private String workLocation;

	@Column(name = "client_geography")
	private String clientGeography;

	@Column(name = "client_country")
	private String clientCountry;
	
	@Column(name = "customer")
	private String customer;
	
	@Column(name = "groupcustomer")
	private String groupCustomer;
	
	@Column(name = "ip")
	private String ip;

	@Column(name = "sp")
	private String sp;

	@Column(name = "subsp")
	private String subsp;

	@Column(name = "brm")
	private String brm;

	@Column(name = "gl")
	private String gl;

	@Column(name = "amid")
	private String amid;

	@Column(name = "am")
	private String am;

	@Column(name = "pl")
	private String pl;

	@Column(name = "group_customer")
	private String group_Customer;

	@Column(name = "project_hash")
	@NotNull
	private Integer projectHash;

	@Column(name = "project_location_wrt_india")
	private String projectLocationWrtIndia;

	@Column(name = "project_type")
	private String projectType;

	@Column(name = "pure_turnkey_flag")
	private String pureTurnkeyFlag;

	@Column(name = "swon_category")
	private String swonCategory;

	@Column(name = "project_cluster")
	private String projectCluster;

	@Column(name = "iou")
	private String iou;

	@Column(name = "sub_iou")
	private String subIou;

	@Column(name = "parent_iou_name")
	private String parentIou;

	@Column(name = "child_iou_name")
	private String childIou;

	@Column(name = "bfs_cluster")
	private String bfsCluster;

	@Column(name = "id")
	@NotNull
	private Integer id;

	@Column(name = "work_geography")
	private String workGeography;

	@Column(name = "expired_ind")
	private String expiredInd;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public LocalDate getProjectChangeDate() {
		return projectChangeDate;
	}

	public void setProjectChangeDate(LocalDate projectChangeDate) {
		this.projectChangeDate = projectChangeDate;
	}

	public String getWorkGeogrphy() {
		return workGeogrphy;
	}

	public void setWorkGeogrphy(String workGeogrphy) {
		this.workGeogrphy = workGeogrphy;
	}

	public String getWorkCountry() {
		return workCountry;
	}

	public void setWorkCountry(String workCountry) {
		this.workCountry = workCountry;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public String getClientGeography() {
		return clientGeography;
	}

	public void setClientGeography(String clientGeography) {
		this.clientGeography = clientGeography;
	}

	public String getClientCountry() {
		return clientCountry;
	}

	public void setClientCountry(String clientCountry) {
		this.clientCountry = clientCountry;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public String getSubsp() {
		return subsp;
	}

	public void setSubsp(String subsp) {
		this.subsp = subsp;
	}

	public String getBrm() {
		return brm;
	}

	public void setBrm(String brm) {
		this.brm = brm;
	}

	public String getGl() {
		return gl;
	}

	public void setGl(String gl) {
		this.gl = gl;
	}

	public String getAmid() {
		return amid;
	}

	public void setAmid(String amid) {
		this.amid = amid;
	}

	public String getAm() {
		return am;
	}

	public void setAm(String am) {
		this.am = am;
	}

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
	}

	public String getGroupCustomer() {
		return groupCustomer;
	}

	public void setGroupCustomer(String groupCustomer) {
		this.groupCustomer = groupCustomer;
	}

	public Integer getProjectHash() {
		return projectHash;
	}

	public void setProjectHash(Integer projectHash) {
		this.projectHash = projectHash;
	}

	public String getProjectLocationWrtIndia() {
		return projectLocationWrtIndia;
	}

	public void setProjectLocationWrtIndia(String projectLocationWrtIndia) {
		this.projectLocationWrtIndia = projectLocationWrtIndia;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getPureTurnkeyFlag() {
		return pureTurnkeyFlag;
	}

	public void setPureTurnkeyFlag(String pureTurnkeyFlag) {
		this.pureTurnkeyFlag = pureTurnkeyFlag;
	}

	public String getSwonCategory() {
		return swonCategory;
	}

	public void setSwonCategory(String swonCategory) {
		this.swonCategory = swonCategory;
	}

	public String getProjectCluster() {
		return projectCluster;
	}

	public void setProjectCluster(String projectCluster) {
		this.projectCluster = projectCluster;
	}

	public String getIou() {
		return iou;
	}

	public void setIou(String iou) {
		this.iou = iou;
	}

	public String getSubIou() {
		return subIou;
	}

	public void setSubIou(String subIou) {
		this.subIou = subIou;
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

	public String getBfsCluster() {
		return bfsCluster;
	}

	public void setBfsCluster(String bfsCluster) {
		this.bfsCluster = bfsCluster;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWorkGeography() {
		return workGeography;
	}

	public void setWorkGeography(String workGeography) {
		this.workGeography = workGeography;
	}

	public String getExpiredInd() {
		return expiredInd;
	}

	public void setExpiredInd(String expiredInd) {
		this.expiredInd = expiredInd;
	}

	public String getGroup_Customer() {
		return group_Customer;
	}

	public void setGroup_Customer(String group_Customer) {
		this.group_Customer = group_Customer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", projectLocation="
				+ projectLocation + ", projectChangeDate=" + projectChangeDate + ", workGeogrphy=" + workGeogrphy
				+ ", workCountry=" + workCountry + ", workLocation=" + workLocation + ", clientGeography="
				+ clientGeography + ", clientCountry=" + clientCountry + ", ip=" + ip + ", sp=" + sp + ", subsp="
				+ subsp + ", brm=" + brm + ", gl=" + gl + ", amid=" + amid + ", am=" + am + ", pl=" + pl
				+ ", groupCustomer=" + groupCustomer + ", projectHash=" + projectHash + ", projectLocationWrtIndia="
				+ projectLocationWrtIndia + ", projectType=" + projectType + ", pureTurnkeyFlag=" + pureTurnkeyFlag
				+ ", swonCategory=" + swonCategory + ", projectCluster=" + projectCluster + ", iou=" + iou + ", subIou="
				+ subIou + ", parentIou=" + parentIou + ", childIou=" + childIou + ", bfsCluster=" + bfsCluster
				+ ", id=" + id + ", workGeography=" + workGeography + ", expiredInd=" + expiredInd + "]";
	}

}
