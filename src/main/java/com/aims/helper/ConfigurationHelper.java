package com.aims.helper;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aims.model.BRIntermediate;
import com.aims.model.HCIntermediate;

public class ConfigurationHelper {
	
	 private static String sql = "select MAX(upload_time) as upload_time,filedata from aims.hc_intermediate group by filedata";
	 
	 private static String brSql = "select MAX(upload_time) as upload_time,filedata,file_id from aims.br_intermediate group by filedata,file_id";
	 
	 private static String sretrieveSql = "SELECT version_no FROM aims.hcversion WHERE current_ind = ?";
	
	public static byte[] getHcInter(JdbcTemplate jdbcTemplate)
	{
		HCIntermediate inter= jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(HCIntermediate.class));
		System.out.println("vale of hc intermed" +inter.getUpload_Time());			
		return inter.getFiledata();
	}
	
	public static byte[] getBrInter(JdbcTemplate jdbcTemplate)
	{
		BRIntermediate inter= jdbcTemplate.queryForObject(brSql, new BeanPropertyRowMapper<>(BRIntermediate.class));
		System.out.println("vale of hc intermed" +inter.getUpload_Time());			
		return inter.getFiledata();
	}


	public static Integer getVersionNo(JdbcTemplate jdbcTemplate)
	{
		return (Integer) jdbcTemplate.queryForObject(sretrieveSql, new Object[] {"Y"}, Integer.class);
	}
}
