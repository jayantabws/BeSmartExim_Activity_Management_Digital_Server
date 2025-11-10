package com.besmartexim.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.besmartexim.dto.request.UserSavedContactsRequest;
import com.besmartexim.dto.response.UserSavedContactsResponse;
import com.besmartexim.service.UserSavedContactsService;

@CrossOrigin
@RestController
@RequestMapping(path = "/activity-management")
public class UserSavedContactsController {

	private static final Logger logger = LoggerFactory.getLogger(UserSavedContactsController.class);

	@Autowired
	private UserSavedContactsService userSavedContactsService;

	@RequestMapping(value = "/savecontact", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> savecontact(@RequestBody @Valid UserSavedContactsRequest userSavedContactsRequest,
			@RequestHeader(value = "accessedBy", required = true) Long accessedBy) throws Exception {
		logger.info("Request : /activity-management/savecontact");
		String result = userSavedContactsService.savecontact(userSavedContactsRequest, accessedBy);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/updatecontact/{contactId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatecontact(@RequestBody @Valid UserSavedContactsRequest userSavedContactsRequest,
			@PathVariable Long contactId, @RequestHeader(value = "accessedBy", required = true) Long accessedBy)
			throws Exception {
		logger.info("Request : /activity-management/updatecontact");
		userSavedContactsService.updatecontact(userSavedContactsRequest, contactId, accessedBy);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/listcontact", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity contactListByUserId(@RequestParam Long userId,
			@RequestHeader(value = "accessedBy", required = true) Long accessedBy) throws Exception {
		logger.info("accessedBy = " + accessedBy);
		UserSavedContactsResponse userSavedContactsResponse = userSavedContactsService.savedContactListByUserId(userId);
		return new ResponseEntity<>(userSavedContactsResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/listcontact/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity contactListAll(@RequestHeader(value = "accessedBy", required = true) Long accessedBy)
			throws Exception {
		logger.info("accessedBy = " + accessedBy);
		UserSavedContactsResponse userSavedContactsResponse = userSavedContactsService.savedContactListAll();
		return new ResponseEntity<>(userSavedContactsResponse, HttpStatus.OK);

	}

}
