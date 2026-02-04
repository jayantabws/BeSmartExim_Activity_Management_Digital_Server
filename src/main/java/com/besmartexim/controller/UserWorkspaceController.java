package com.besmartexim.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.besmartexim.dto.request.UserWorkspaceRequest;
import com.besmartexim.dto.request.WorkspaceRequest;
import com.besmartexim.dto.response.UserWorkspaceResponse;
import com.besmartexim.dto.response.WorkspaceResponse;
import com.besmartexim.service.UserWorkspaceService;
import com.besmartexim.service.WorkspaceService;

@CrossOrigin
@RestController
@RequestMapping(path = "/activity-management")
public class UserWorkspaceController {

	private static final Logger logger = LoggerFactory.getLogger(UserWorkspaceController.class);

	@Autowired
	private UserWorkspaceService userWorkspaceService;

	@Autowired
	private WorkspaceService workspaceService;

	@PostMapping(value = "/workspace", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> workspaceCreate(@RequestBody @Valid WorkspaceRequest workspaceRequest,
			@RequestHeader(required = true) Long accessedBy) throws Exception {
		logger.info("Request : /activity-management/workspace");
		Long workspaceId;
		workspaceId = workspaceService.workspaceCreate(workspaceRequest, accessedBy);
		return new ResponseEntity<>(workspaceId, HttpStatus.CREATED);
	}

	@PutMapping(value = "/workspace/{workspaceId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> workspaceUpdate(@RequestBody @Valid WorkspaceRequest workspaceRequest,
			@PathVariable Long workspaceId, @RequestHeader(required = true) Long accessedBy)
			throws Exception {
		logger.info("accessedBy = " + accessedBy);
		workspaceService.workspaceUpdate(workspaceRequest, workspaceId, accessedBy);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping(value = "/workspace/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> workspaceList(@RequestParam Long userId,
			@RequestHeader(required = true) Long accessedBy) throws Exception {
		logger.info("accessedBy = " + accessedBy);

		WorkspaceResponse workspaceResponse = workspaceService.workspaceListByUserId(userId);

		return new ResponseEntity<>(workspaceResponse, HttpStatus.OK);

	}

	@PostMapping(value = "/workspace/savesearch", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> workspaceUpdate(@RequestBody UserWorkspaceRequest userWorkspaceRequest,
			@RequestHeader(required = true) Long accessedBy) throws Exception {
		logger.info("accessedBy = " + accessedBy);

		if (null == userWorkspaceRequest.getWorkspace_id()) {
			throw new Exception("workspace_id is Blank");
		}
		userWorkspaceService.saveSearch(userWorkspaceRequest, accessedBy);

		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@PutMapping(value = "/workspace/removesearch", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> workspaceRemove(@RequestParam(required = true) Long userWorkspaceId,
			@RequestHeader(required = true) Long accessedBy) throws Exception {
		logger.info("accessedBy = " + accessedBy);

		userWorkspaceService.removeSearch(userWorkspaceId, accessedBy);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping(value = "/workspace/searchByUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserWorkspaceResponse> workspaceSearchByUser(
			@RequestParam(required = true) Long createdBy,
			@RequestParam(required = false) Long workspaceId,
			@RequestHeader(required = true) Long accessedBy) throws Exception {
		logger.info("accessedBy = " + accessedBy);

		UserWorkspaceResponse userWorkspaceResponse = userWorkspaceService.workspaceSearchByUser(workspaceId,
				createdBy);

		return new ResponseEntity<>(userWorkspaceResponse, HttpStatus.OK);

	}

}
