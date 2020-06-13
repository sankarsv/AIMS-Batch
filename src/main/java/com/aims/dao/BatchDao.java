package com.aims.dao;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.aims.bo.BillingDetails;
import com.aims.model.BRIntermediate;
import com.aims.model.BillingMaster;
import com.aims.model.BillingVersion;
import com.aims.model.ClarityIntermediate;
import com.aims.model.ClarityMaster;
import com.aims.model.HCIntermediate;


public class BatchDao {
	
	private static String sql = "select MAX(upload_time) as upload_time,filedata from aims.hc_intermediate group by filedata";

	private static String brSql = "select MAX(upload_time) as upload_time,filedata,file_id from aims.br_intermediate group by filedata,file_id";
	
	private static String claritySql = "select MAX(upload_time) as upload_time,filedata,file_id from aims.clarity_intermediate group by filedata,file_id";
	
	private static String sqlUpdate = "UPDATE aims.hcversion set current_ind = :ind where current_ind = :indold";
	
	private static String clarityVersionsql = " select nextval('aims.seq_clarity_version')";
	
	private static String clarityVersionUpdateSql = "UPDATE aims.billingversion SET CLARITYVERSION= :version where periodmonth = :month and year = :year";
	
	private static String employeeOfficeIdUpdateSql = "UPDATE aims.employee SET office_id= :officeId where employee_id = :employeeId";
	
	private static String deleteHCIntermediate = "delete from aims.hc_intermediate";
	
	private static String deleteBRIntermediate = "delete from aims.br_intermediate";
	
	private static String deleteClarityIntermediate = "delete from aims.clarity_intermediate";
	
	private static String sqlWrite = "INSERT INTO aims.hcversion(version_no,load_date, description, current_ind)"
			+ "	VALUES (nextval('aims.seq_version_no'), :loadDate, :desc, :curr)";
	
	private static String sqlBillingVersionWrite = "INSERT INTO aims.billingversion (brm_empno, periodmonth, year, version, freezeind, location)"
			+ "	VALUES (:brm_empno, :month, :year, nextval('aims.seq_bill_version'), :freeze, :location)";
	
	private String sqlBillRateUpdate ="UPDATE aims.billrate set billrate = :billrate where billing_employee_id = :empid";
	
	//brmname=:brmname,officeid=:officeid,employee_name=:empname
	private static String sqlBillMasterUpdate ="UPDATE aims.billingmaster set dmname = :dmname, won= :won, onsite_offshore=:onof, sto=:sto, billablehours=:billablehours,"
			+ " billabledays=:billabledays, effort=:effort, extrahours=:extrahours, extrabilling=:extrabilling, billableamount=:billableamount,"
			+ " remarks1=:remarks1,remarks2=:remarks2 where employee_id = :empid and version=:version";
	
	private static String sqlBillRateInsert ="INSERT INTO aims.billrate(billing_employee_id, billrate, currencr, enddate, startdate, billing_version) "
			+ "VALUES (:empid, :billrate, :currency, :enddate, :startdate, :version)";
	
	private static String sqlClarityMasterInsert ="INSERT INTO aims.claritymaster(clarity_id, version, transactionclass, cccio, resourcemanager, timesheetdepartment, lastnamefirstname, resourceid, officeid, cin, sumofhours, averagerate, ratewithouttax) "
			+ "VALUES (nextval('aims.seq_clarity_id'), :version, :transactionclass, :cccio, :resourcemanager, :timesheetdepartment, :lastnamefirstname, :resourceid, :officeid, :cin, :sumofhours, :averagerate, :ratewithouttax)";
	
	private static String sqlBillMasterInsert ="INSERT INTO aims.billingmaster(version, employee_id, dmname, won, onsite_offshore, sto, billablehours, billabledays, effort, extrahours, "
			+ "extrabilling, billableamount, remarks1, remarks2) "
			+ "VALUES (:version, :empid, :dmname, :won, :onsiteoffshore, :sto, :billablehrs, :billabledays, :effort, :extrahours, :extrabilling, :billableamount, :remarks1, :remarks2)";
			
	
	private static String sretrieveSql = "SELECT version_no FROM aims.hcversion WHERE current_ind = ?";
	
	private static String retrieveBrmEmpId= "SELECT brm_empid FROM aims.portfolio WHERE brmname = ? limit 1";
	
	private static String retrieveDmId= "SELECT dm_emp_id FROM aims.portfolio WHERE dm_name = ? limit 1";
	
	private static String retrieveclarityVersSql= "SELECT distinct clarityversion from aims.billingversion where periodmonth=? and year=?";
	
	private static String checkIfbillingVersionSql = "SELECT version FROM aims.billingversion WHERE brm_empno = ? and year =? and periodmonth=? and location=?";
	
	private static String retrieveBillingVersionSql = "select version from aims.billingversion WHERE brm_empno = ? and periodmonth=? and year =? and location=?";
	
	private NamedParameterJdbcTemplate myJDBC = null;
	
	private JdbcTemplate jdbcTemplate = null; 	
		
	public BatchDao(DataSource ds)
	{
		myJDBC= new NamedParameterJdbcTemplate(ds);
		jdbcTemplate = new JdbcTemplate(ds);		
	}
	
	public void updateHCVersion(KeyHolder holder)
	{
		
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("ind", "N")
				.addValue("indold", "Y");;
		myJDBC.update(sqlUpdate, parameters, holder);
	}
	
	public void updateClarityVersion(KeyHolder holder,String month,int year) {
		
		System.out.println("month of carity version" +month);
		
		System.out.println("year of carity version" +year);
		
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("version", retrieveClarityVersionFromDual()).addValue("month", month)
				
				.addValue("year", year);
		
		int value=myJDBC.update(clarityVersionUpdateSql, parameters, holder);		
		//, new Object[] {month,year});
		System.out.println("update count of carity version" +value);
	}
	
	public int saveEmployeeOfficeId(KeyHolder holder, String officeId, String empId) {

		SqlParameterSource parameters = new MapSqlParameterSource().addValue("officeId", officeId)

				.addValue("employeeId", Integer.parseInt(empId));

		return myJDBC.update(employeeOfficeIdUpdateSql, parameters, holder);

	}
	
	public void insertHCVersion(KeyHolder holder)
	{
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		SqlParameterSource insParam = new MapSqlParameterSource()
				
				.addValue("loadDate", sqlDate)
				.addValue("desc", "Head count report")
				.addValue("curr", "Y");
		myJDBC.update(sqlWrite, insParam, holder);
	}
	
	public void insertBillingVersion(BillingVersion bv)
	{
		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource insParam = new MapSqlParameterSource()
				
				.addValue("brm_empno", bv.getBrmEmpNo())
				.addValue("month", bv.getMonth())
				.addValue("year", bv.getYear())
				.addValue("freeze", bv.getFreezeInd())
				.addValue("location", bv.getLocation());

		myJDBC.update(sqlBillingVersionWrite, insParam, holder);
	}
	
	public void insertClarityMaster(ClarityMaster cm)
	{
		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource insParam = new MapSqlParameterSource()
		//:version, :transactionclass, :resourcemanager, :timesheetdepartment, :lastnamefirstname, :resourceid, :officeid, :cin, :sumofhours, :averagerate, :ratewithouttax)";
				.addValue("version", cm.getVersion())
				.addValue("transactionclass", cm.getTransactionClass())
				.addValue("cccio", cm.getCccio())
				.addValue("resourcemanager", cm.getResourceManager())
				.addValue("timesheetdepartment", cm.getTimesheetDept())
				.addValue("lastnamefirstname", cm.getFullName())
				.addValue("resourceid", cm.getResourceId())
				.addValue("officeid", cm.getOfficeId())
				.addValue("cin", cm.getCin())
				.addValue("sumofhours", cm.getSumOfHrs())
				.addValue("averagerate", cm.getAvgRate())
				.addValue("ratewithouttax", cm.getRateWoTax());

		myJDBC.update(sqlClarityMasterInsert, insParam, holder);
	}
	
	public boolean checkBillingVersionExist(BillingVersion bv) {
		try {
			jdbcTemplate.queryForObject(checkIfbillingVersionSql,
					new Object[] { bv.getBrmEmpNo(), bv.getYear(), bv.getMonth(), bv.getLocation() }, Integer.class);
		} catch (EmptyResultDataAccessException ex) {
			return false;
		}

		return true;
	}
	
	private Integer retrieveClarityVersionFromDual()
	{
		return (Integer) jdbcTemplate.queryForObject(clarityVersionsql, new Object[] {}, Integer.class);
	}
	
	
	public Integer retrieveHCVersion()
	{
		return (Integer) jdbcTemplate.queryForObject(sretrieveSql, new Object[] {"Y"}, Integer.class);
	}
	
	public Integer retrieveclarityVersion(String month,int year)
	{
		return (Integer) jdbcTemplate.queryForObject(retrieveclarityVersSql, new Object[] {month,year}, Integer.class);
	}
	
	public Integer retrievebrmEmpId(String name)
	{
		try {
		return (Integer) jdbcTemplate.queryForObject(retrieveBrmEmpId, new Object[] {name}, Integer.class);
		}catch (EmptyResultDataAccessException ex) {
			return 0;
		}
	}
	
	public Integer retrieveDmEmpId(String name)
	{
		try {
		return (Integer) jdbcTemplate.queryForObject(retrieveDmId, new Object[] {name}, Integer.class);
		}catch (EmptyResultDataAccessException ex) {
			return 0;
		}
	}
	
	public Integer retrieveBillingVersion(BillingDetails bd,String brmEmpId)
	{
		LocalDate currentDate = LocalDate.now();

		String month =currentDate.getMonth().toString();
		int year = currentDate.getYear();
		return (Integer) jdbcTemplate.queryForObject(retrieveBillingVersionSql,new Object[] {brmEmpId,month,year,bd.getOnsiteOffshore()},Integer.class);
	}
	
	public int upDateBillRate(KeyHolder holder,BillingDetails bd)
	{		
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("billrate", bd.getBillRate())
				.addValue("empid", bd.getEmpId());
		return myJDBC.update(sqlBillRateUpdate, parameters, holder);
	}
	
	public int deleteHCIntermediate()
	{		
		return myJDBC.update(deleteHCIntermediate, new MapSqlParameterSource());
	}
	
	public int deleteBRIntermediate()
	{		
		return myJDBC.update(deleteBRIntermediate, new MapSqlParameterSource());
	}
	
	public int deleteClarityIntermediate()
	{		
		return myJDBC.update(deleteClarityIntermediate, new MapSqlParameterSource());
	}
	
	public int upDateBillMaster(KeyHolder holder,BillingMaster bd)
	{	
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("dmname", bd.getDmName())
				.addValue("won", bd.getWonNumber()).addValue("onof", bd.getOnsiteOffshore()).addValue("sto", bd.getSto())
				.addValue("billablehours", bd.getBillablehrs()).addValue("billabledays", bd.getBillableDays())
				.addValue("effort", bd.getEffort()).addValue("extrahours", bd.getExtraHours()).addValue("extrabilling", bd.getExtraBilling())
				.addValue("billableamount", bd.getBillableAmount()).addValue("remarks1", bd.getRemarks1())
				.addValue("remarks2", bd.getRemarks2()).addValue("empid", bd.getEmpId()).addValue("version", bd.getVersionNo());
		
		//.addValue("brmname", bd.getBrmName()).addValue("officeid", bd.getOfficeId()).addValue("empname", bd.getEmpName())
		return myJDBC.update(sqlBillMasterUpdate, parameters, holder);
	}

	
	public void insertBillRate(KeyHolder holder,BillingDetails bd,int billingVersion)
	{		
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("empid", bd.getEmpId()).addValue("billrate", bd.getBillRate()).addValue("currency", null)
				.addValue("enddate", null).addValue("startdate", sqlDate).addValue("version", billingVersion);
		myJDBC.update(sqlBillRateInsert, parameters, holder);
	}

	public void insertBillingDetails(KeyHolder holder,BillingMaster bd)
	{		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("version", bd.getVersionNo()).addValue("empid", bd.getEmpId()).addValue("dmname", bd.getDmName())
				.addValue("won", bd.getWonNumber()).addValue("onsiteoffshore", bd.getOnsiteOffshore()).addValue("sto", bd.getSto())
				.addValue("billablehrs", bd.getBillablehrs()).addValue("billabledays", bd.getBillableDays())
				.addValue("effort", bd.getEffort()).addValue("extrahours", bd.getExtraHours()).addValue("extrabilling", bd.getExtraBilling())
				.addValue("billableamount", bd.getBillableAmount()).addValue("remarks1", bd.getRemarks1())
				.addValue("remarks2", bd.getRemarks2());
		//.addValue("brm", bd.getBrmName()).addValue("officeid", bd.getOfficeId()).addValue("empname", bd.getEmpName());
		myJDBC.update(sqlBillMasterInsert, parameters, holder);
	}
	
	public  byte[] getHcInter() {
		HCIntermediate inter = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(HCIntermediate.class));
		return inter.getFiledata();
	}
	
	public  byte[] getClarityInter() {
		ClarityIntermediate inter = jdbcTemplate.queryForObject(claritySql, new BeanPropertyRowMapper<>(ClarityIntermediate.class));
		return inter.getFiledata();
	}

	public  byte[] getBrInter() {
		BRIntermediate inter = jdbcTemplate.queryForObject(brSql, new BeanPropertyRowMapper<>(BRIntermediate.class));
		return inter.getFiledata();
	}

	public Integer getVersionNo() {
		return (Integer) jdbcTemplate.queryForObject(sretrieveSql, new Object[] { "Y" }, Integer.class);
	}


}
