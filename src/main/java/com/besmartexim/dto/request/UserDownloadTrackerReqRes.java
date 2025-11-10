package com.besmartexim.dto.request;

import java.util.List;

public class UserDownloadTrackerReqRes {

	private Long userId;
	private List<Long> downloadedRecords;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getDownloadedRecords() {
		return downloadedRecords;
	}

	public void setDownloadedRecords(List<Long> downloadedRecords) {
		this.downloadedRecords = downloadedRecords;
	}

}
