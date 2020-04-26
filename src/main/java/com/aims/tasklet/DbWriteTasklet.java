package com.aims.tasklet;

import javax.sql.DataSource;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class DbWriteTasklet implements Tasklet {

	private DataSource datasource = null;;

	private String sqlUpdate = "UPDATE aims.hcversion set current_ind = :ind where current_ind = :indold";

	private String sqlWrite = "INSERT INTO aims.hcversion(version_no,load_date, description, current_ind)"
			+ "	VALUES (nextval('aims.seq_version_no'), :loadDate, :desc, :curr)";

	public DbWriteTasklet(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		NamedParameterJdbcTemplate myJDBC = new NamedParameterJdbcTemplate(datasource);
		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("ind", "N")
				.addValue("indold", "Y");;
		myJDBC.update(sqlUpdate, parameters, holder);

		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		SqlParameterSource insParam = new MapSqlParameterSource()
				
				.addValue("loadDate", sqlDate)
				.addValue("desc", "Head count report")
				.addValue("curr", "Y");
		myJDBC.update(sqlWrite, insParam, holder);
		
		String sretrieveSql = "SELECT version_no FROM aims.hcversion WHERE current_ind = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
		int versionNo=(Integer) jdbcTemplate.queryForObject(sretrieveSql, new Object[] {"Y"}, Integer.class);

		
		chunkContext.getStepContext().getStepExecution().getExecutionContext().put("versionNo", versionNo);

		return RepeatStatus.FINISHED;
	}

}
