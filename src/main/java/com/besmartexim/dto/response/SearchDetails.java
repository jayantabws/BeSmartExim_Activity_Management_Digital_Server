package com.besmartexim.dto.response;

import java.util.Date;

import com.besmartexim.dto.request.UserSearchRequest;

public class SearchDetails {

	public Long searchId;
	
	//public String searchParams;
	public UserSearchRequest userSearchQuery;
	
	 
	public Date createdDate;	
	public long totalRecords;	
	public Long createdBy;
	public String createdByName;
	public String createdByEmail;
	public String isDownloaded;
	public Date downloadedDate;
	public String downloadedBy;
	
	
	public Long getSearchId() {
		return searchId;
	}
	public void setSearchId(Long searchId) {
		this.searchId = searchId;
	}
	public UserSearchRequest getUserSearchQuery() {
		return userSearchQuery;
	}
	public void setUserSearchQuery(UserSearchRequest userSearchQuery) {
		this.userSearchQuery = userSearchQuery;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getCreatedByEmail() {
		return createdByEmail;
	}
	public void setCreatedByEmail(String createdByEmail) {
		this.createdByEmail = createdByEmail;
	}
	public String getIsDownloaded() {
		return isDownloaded;
	}
	public void setIsDownloaded(String isDownloaded) {
		this.isDownloaded = isDownloaded;
	}
	public Date getDownloadedDate() {
		return downloadedDate;
	}
	public void setDownloadedDate(Date downloadedDate) {
		this.downloadedDate = downloadedDate;
	}
	public String getDownloadedBy() {
		return downloadedBy;
	}
	public void setDownloadedBy(String downloadedBy) {
		this.downloadedBy = downloadedBy;
	}
	
	
	
	

	
}
