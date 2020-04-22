/**
 * 
 */
package com.aims.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ShanmugapriyaJ
 *
 */
@Entity
@Table(name="HC_INTERMEDIATE",schema="aims")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HCIntermediate {
	@Id 
	@Column(name="UPLOAD_TIME")
	@NotNull
	private Date upload_Time;
	
	public Date getUpload_Time() {
		return upload_Time;
	}

	public void setUpload_Time(Date upload_Time) {
		this.upload_Time = upload_Time;
	}

	public byte[] getFiledata() {
		return filedata;
	}

	public void setFiledata(byte[] filedata) {
		this.filedata = filedata;
	}

	@Lob
	@Column(name="FILEDATA")
	@NotNull
	private byte[] filedata;

		
}
