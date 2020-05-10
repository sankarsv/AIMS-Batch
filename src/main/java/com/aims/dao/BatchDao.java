package com.aims.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.aims.bo.BillingDetails;
import com.aims.model.BillingVersion;

public class BatchDao {
	
	private String sqlUpdate = "UPDATE aims.hcversion set current_ind = :ind where current_ind = :indold";
	
	private String sqlWrite = "INSERT INTO aims.hcversion(version_no,load_date, description, current_ind)"
			+ "	VALUES (nextval('aims.seq_version_no'), :loadDate, :desc, :curr)";
	
	private String sqlBillingVersionWrite = "INSERT INTO aims.billingversion (brm, periodmonth, year, version, freezeind)"
			+ "	VALUES (:brm, :month, :year, nextval('aims.seq_bill_version'), :freeze)";
	
	private String sqlBillRateUpdate ="UPDATE aims.billrate set billrate = :billrate where employee_id = :empid";
	
	private String sqlBillRateInsert ="INSERT INTO aims.billrate(employee_id, billrate, currencycr, enddate, startdate) "
			+ "VALUES (:empid, :billrate, :currency, :enddate, :startdate)";
	
	String sretrieveSql = "SELECT version_no FROM aims.hcversion WHERE current_ind = ?";
	
	String checkIfbillingVersionSql = "SELECT version FROM aims.billingversion WHERE brm = ? and year =? and periodmonth=?";
	
	String retrieveBillingVersionSql = "select max(version) as version_no from aims.billingversion";
	
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
	
	public boolean checkBillingVersionExist(BillingVersion bv)
	{
		int returnval = (Integer) jdbcTemplate.queryForObject(checkIfbillingVersionSql, new Object[] {bv.getBrmRef(),bv.getYear(),bv.getMonth()}, Integer.class);
		if(returnval>0)
			return true;
		return false;
	}
	
	public Integer retrieveHCVersion()
	{
		return (Integer) jdbcTemplate.queryForObject(sretrieveSql, new Object[] {"Y"}, Integer.class);
	}
	
	public Integer retrieveBillingVersion()
	{
		return (Integer) jdbcTemplate.queryForObject(retrieveBillingVersionSql,Integer.class);
	}
	
	public int upDateBillRate(KeyHolder holder,BillingDetails bd)
	{		
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("billrate", bd.getBillRate())
				.addValue("empid", bd.getEmpId());
		return myJDBC.update(sqlBillRateUpdate, parameters, holder);
	}
	
	public void insertBillRate(KeyHolder holder,BillingDetails bd)
	{		
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("empid", bd.getEmpId()).addValue("billrate", bd.getBillRate()).addValue("currency", null)
				.addValue("enddate", null).addValue("startdate", sqlDate);
		myJDBC.update(sqlBillRateInsert, parameters, holder);
	}

}
