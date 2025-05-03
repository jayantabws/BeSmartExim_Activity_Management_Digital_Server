package com.besmartexim.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.besmartexim.database.entity.UserDownloadTracker;
import com.besmartexim.database.repository.UserDownloadTrackerRepo;
import com.besmartexim.dto.request.UserDownloadTrackerReqRes;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ActivityManagementService {

	private static final Logger logger = LoggerFactory.getLogger(ActivityManagementService.class);

	@Autowired
	private UserDownloadTrackerRepo userDownloadTrackerRepo;

	private ObjectMapper objectMapper = new ObjectMapper();

	public Long saveOrUpdateTracker( UserDownloadTrackerReqRes userDownloadTrackerRequest, Long accessedBy) {

		UserDownloadTracker userDownloadTracker = null;

		try {

			List<UserDownloadTracker> data = userDownloadTrackerRepo
					.findByUserId(userDownloadTrackerRequest.getUserId());
			if (data.isEmpty()) {
				userDownloadTracker = new UserDownloadTracker();
				userDownloadTracker.setUserId(userDownloadTrackerRequest.getUserId());
				userDownloadTracker.setCreatedDate(new Date());
				userDownloadTracker.setDownloadedRecords(
						objectMapper.writeValueAsString(userDownloadTrackerRequest.getDownloadedRecords()));
				userDownloadTracker.setDownloadedRecordCount(Long.parseLong(""+userDownloadTrackerRequest.getDownloadedRecords().size()));

			} else {
				userDownloadTracker = data.get(0);
				userDownloadTracker.setDownloadedRecords(
						objectMapper.writeValueAsString(userDownloadTrackerRequest.getDownloadedRecords()));
				userDownloadTracker.setDownloadedRecordCount(Long.parseLong(""+userDownloadTrackerRequest.getDownloadedRecords().size()));
			}
			userDownloadTracker = userDownloadTrackerRepo.save(userDownloadTracker);

		} catch (Exception e) {
			logger.error("Exception: " + e);
			return 0L;
		}

		return userDownloadTracker.getId();
	}

	public String trackerByUserId(Long userId) {
		UserDownloadTracker userDownloadTracker = null;
		try {
			List<UserDownloadTracker> data = userDownloadTrackerRepo.findByUserId(userId);
			if (data.isEmpty()) {
				return null;
			} else {
				userDownloadTracker = data.get(0);
				UserDownloadTrackerReqRes userDownloadTrackerReqRes = new UserDownloadTrackerReqRes();
				userDownloadTrackerReqRes.setUserId(userId);
				//List<Long> array = objectMapper.readValue(userDownloadTracker.getDownloadedRecords(), ArrayList.class);
				//List<Long> array = Arrays.asList(userDownloadTracker.getDownloadedRecords());
				//userDownloadTrackerReqRes.setDownloadedRecords(array);

				return userDownloadTracker.getDownloadedRecords();
			}
		} catch (Exception e) {
			logger.error("Exception: " + e);
			return null;
		}

	}

}
