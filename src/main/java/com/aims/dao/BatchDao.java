package com.aims.dao;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.aims.bo.BillingDetails;
import com.aims.model.BillingMaster;
import com.aims.model.BillingVersion;

public class BatchDao {
	
	private String sqlUpdate = "UPDATE aims.hcversion set current_ind = :ind where current_ind = :indold";
	
	private String deleteHCIntermediate = "delete from aims.hc_intermediate";
	
	private String deleteBRIntermediate = "delete from aims.br_intermediate";
	
	private String sqlWrite = "INSERT INTO aims.hcversion(version_no,load_date, description, current_ind)"
			+ "	VALUES (nextval('aims.seq_version_no'), :loadDate, :desc, :curr)";
	
	private String sqlBillingVersionWrite = "INSERT INTO aims.billingversion (brm, periodmonth, year, version, freezeind)"
			+ "	VALUES (:brm, :month, :year, nextval('aims.seq_bill_version'), :freeze)";
	
	private String sqlBillRateUpdate ="UPDATE aims.billrate set billrate = :billrate where billing_employee_id = :empid";
	
	private String sqlBillMasterUpdate ="UPDATE aims.billingmaster set dmname = :dmname, won= :won, onsite_offshore=:onof, sto=:sto, billablehours=:billablehours,"
			+ " billabledays=:billabledays, effort=:effort, extrahours=:extrahours, extrabilling=:extrabilling, billableamount=:billableamount,"
			+ " remarks1=:remarks1,remarks2=:remarks2, brmname=:brmname,officeid=:officeid,employee_name=:empname where employee_id = :empid and version=:version";
	
	private String sqlBillRateInsert ="INSERT INTO aims.billrate(billing_employee_id, billrate, currencr, enddate, startdate, billing_version) "
			+ "VALUES (:empid, :billrate, :currency, :enddate, :startdate, :version)";
	
	private String sqlBillMasterInsert ="INSERT INTO aims.billingmaster(version, employee_id, dmname, won, onsite_offshore, sto, billablehours, billabledays, effort, extrahours, "
			+ "extrabilling, billableamount, remarks1, remarks2, brmname, officeid, employee_name) "
			+ "VALUES (:version, :empid, :dmname, :won, :onsiteoffshore, :sto, :billablehrs, :billabledays, :effort, :extrahours, :extrabilling, :billableamount, :remarks1, :remarks2"
			+ ", :brm, :officeid, :empname)";
	
	String sretrieveSql = "SELECT version_no FROM aims.hcversion WHERE current_ind = ?";
	
	String checkIfbillingVersionSql = "SELECT version FROM aims.billingversion WHERE brm = ? and year =? and periodmonth=?";
	
	String retrieveBillingVersionSql = "select version from aims.billingversion WHERE brm = ? and periodmonth=? and year =?";
	
	private NamedParameterJdbcTemplate myJDBC = null;
	
	private JdbcTemplate jdbcTemplate = null; 
	
	public BatchDao(DataSource datasource)
	{
		myJDBC= new NamedParameterJdbcTemplate(datasource);
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public void updateHCVersion(KeyHolder holder)
	{
		
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("ind", "N")
				.addValue("indold", "Y");;
		myJDBC.update(sqlUpdate, parameters, holder);
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
				
				.addValue("brm", bv.getBrmRef())
				.addValue("month", bv.getMonth())
				.addValue("year", bv.getYear())
				.addValue("freeze", bv.getFreezeInd());
		myJDBC.update(sqlBillingVersionWrite, insParam, holder);
	}
	
	public boolean checkBillingVersionExist(BillingVersion bv) {
		try {
			jdbcTemplate.queryForObject(checkIfbillingVersionSql,
					new Object[] { bv.getBrmRef(), bv.getYear(), bv.getMonth() }, Integer.class);
		} catch (EmptyResultDataAccessException ex) {
			return false;
		}

		return true;
	}
	
	public Integer retrieveHCVersion()
	{
		return (Integer) jdbcTemplate.queryForObject(sretrieveSql, new Object[] {"Y"}, Integer.class);
	}
	
	public Integer retrieveBillingVersion(BillingDetails bd)
	{
		LocalDate currentDate = LocalDate.now();

		String month =currentDate.getMonth().toString();
		int year = currentDate.getYear();
		return (Integer) jdbcTemplate.queryForObject(retrieveBillingVersionSql,new Object[] {bd.getBrm(),month,year},Integer.class);
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
	
	public int upDateBillMaster(KeyHolder holder,BillingMaster bd)
	{	
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("dmname", bd.getDmName())
				.addValue("won", bd.getWonNumber()).addValue("onof", bd.getOnsiteOffshore()).addValue("sto", bd.getSto())
				.addValue("billablehours", bd.getBillablehrs()).addValue("billabledays", bd.getBillableDays())
				.addValue("effort", bd.getEffort()).addValue("extrahours", bd.getExtraHours()).addValue("extrabilling", bd.getExtraBilling())
				.addValue("billableamount", bd.getBillableAmount()).addValue("remarks1", bd.getRemarks1())
				.addValue("remarks2", bd.getRemarks2()).addValue("brmname", bd.getBrmName()).addValue("officeid", bd.getOfficeId())
				.addValue("empname", bd.getEmpName()).addValue("empid", bd.getEmpId()).addValue("version", bd.getVersionNo());
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
				.addValue("remarks2", bd.getRemarks2()).addValue("brm", bd.getBrmName()).addValue("officeid", bd.getOfficeId())
				.addValue("empname", bd.getEmpName());
		myJDBC.update(sqlBillMasterInsert, parameters, holder);
	}


}
