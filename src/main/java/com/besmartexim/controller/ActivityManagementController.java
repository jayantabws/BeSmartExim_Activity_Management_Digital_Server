package com.besmartexim.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.besmartexim.dto.request.UserDownloadTrackerReqRes;
import com.besmartexim.service.ActivityManagementService;

@CrossOrigin
@RestController
@RequestMapping(path = "/activity-management")
public class ActivityManagementController {

	private static final Logger logger = LoggerFactory.getLogger(ActivityManagementController.class);

	@Autowired
	private ActivityManagementService activityManagementService;

	@RequestMapping(value = "/download-tracker/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveOrUpdateTracker(@RequestBody UserDownloadTrackerReqRes userDownloadTrackerRequest,
			@RequestHeader(value = "accessedBy", required = true) Long accessedBy) throws Exception {
		logger.info("Request : /download-tracker/update");
		Long trackerId;
		trackerId = activityManagementService.saveOrUpdateTracker(userDownloadTrackerRequest, accessedBy);
		return new ResponseEntity<>(trackerId, HttpStatus.OK);
	}

	@RequestMapping(value = "/download-tracker/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String trackerByUserId(@RequestParam Long userId,
			@RequestHeader(value = "accessedBy", required = true) Long accessedBy) throws Exception {
		logger.info("accessedBy = " + accessedBy);

		String userDownloadTracker = activityManagementService.trackerByUserId(userId);

		return userDownloadTracker;

	}
}
