package com.besmartexim.database.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_download_tracker")
public class UserDownloadTracker {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "downloaded_records")
	private String downloadedRecords;

	@Column(name = "downloaded_record_count")
	private Long downloadedRecordCount;

	@Column(name = "created_date")
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDownloadedRecords() {
		return downloadedRecords;
	}

	public void setDownloadedRecords(String downloadedRecords) {
		this.downloadedRecords = downloadedRecords;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getDownloadedRecordCount() {
		return downloadedRecordCount;
	}

	public void setDownloadedRecordCount(Long downloadedRecordCount) {
		this.downloadedRecordCount = downloadedRecordCount;
	}

}
