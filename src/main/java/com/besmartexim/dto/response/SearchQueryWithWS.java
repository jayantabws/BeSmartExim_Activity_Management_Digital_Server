package com.besmartexim.dto.response;

import java.util.Date;
import java.util.List;

import com.besmartexim.dto.request.TreadType;

public class SearchQueryWithWS {

	private Long id;
	private Long workspaceId;
	private Long searchId;
	private String name;
	private String description;
	private String isActive;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	
	public long totalRecords;
	public String isDownloaded;
	private TreadType tradeType;
	private String fromDate;
	private String toDate;
	private List<String> countryCode;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWorkspaceId() {
		return workspaceId;
	}
	public void setWorkspaceId(Long workspaceId) {
		this.workspaceId = workspaceId;
	}
	public Long getSearchId() {
		return searchId;
	}
	public void setSearchId(Long searchId) {
		this.searchId = searchId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getIsDownloaded() {
		return isDownloaded;
	}
	public void setIsDownloaded(String isDownloaded) {
		this.isDownloaded = isDownloaded;
	}
	public TreadType getTradeType() {
		return tradeType;
	}
	public void setTradeType(TreadType tradeType) {
		this.tradeType = tradeType;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public List<String> getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(List<String> countryCode) {
		this.countryCode = countryCode;
	}
	
	
	
}
