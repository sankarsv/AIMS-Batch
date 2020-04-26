package com.aims.helper;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aims.model.HCIntermediate;

public class ConfigurationHelper {
	
	 private static String sql = "select MAX(upload_time) as upload_time,filedata from aims.hc_intermediate group by filedata";
	 
	 private static String sretrieveSql = "SELECT version_no FROM aims.hcversion WHERE current_ind = ?";
	
	public static byte[] getHcInter(JdbcTemplate jdbcTemplate)
	{
		HCIntermediate inter= jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(HCIntermediate.class));
		System.out.println("vale of hc intermed" +inter.getUpload_Time());			
		return inter.getFiledata();
	}

	public static Integer getVersionNo(JdbcTemplate jdbcTemplate)
	{
		return (Integer) jdbcTemplate.queryForObject(sretrieveSql, new Object[] {"Y"}, Integer.class);
	}
}
