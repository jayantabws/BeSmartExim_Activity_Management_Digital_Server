package com.besmartexim.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.besmartexim.database.entity.UserWorkspace;
import com.besmartexim.database.repository.UserWorkspaceRepository;
import com.besmartexim.dto.request.UserSubscriptionDetailsRequest;
import com.besmartexim.dto.request.UserWorkspaceRequest;
import com.besmartexim.dto.response.SearchDetails;
import com.besmartexim.dto.response.SearchDetailsResponse;
import com.besmartexim.dto.response.SearchQueryWithWS;
import com.besmartexim.dto.response.UserSubscriptionDetails;
import com.besmartexim.dto.response.UserSubscriptionList;
import com.besmartexim.dto.response.UserWorkspaceResponse;

@Service
public class UserWorkspaceService {

	@Autowired
	private UserWorkspaceRepository userWorkspaceRepository;

	public void saveSearch(UserWorkspaceRequest request, Long accessedBy) throws Exception {

		Long workspaceId = request.getWorkspace_id();
		Long searchId = request.getSearch_id();
		String name = request.getName();
		String description = request.getDescription();
		String isActive = request.getIs_active();

		UserWorkspace userWorkspaceEntity = new UserWorkspace();

		if (request.getId() == null || request.getId().equals("")) {
			userWorkspaceEntity.setCreatedBy(accessedBy);
			userWorkspaceEntity.setCreatedDate(new Date());
			userWorkspaceEntity.setWorkspaceId(workspaceId);
			userWorkspaceEntity.setSearchId(searchId);
			userWorkspaceEntity.setName(name);
			userWorkspaceEntity.setDescription(description);
			userWorkspaceEntity.setIsActive(isActive);
			userWorkspaceEntity.setIsDelete("N");

			userWorkspaceRepository.save(userWorkspaceEntity);
		} else {
			UserWorkspace exisUserWorkspaceEntity = userWorkspaceRepository.findById(request.getId()).get();
			exisUserWorkspaceEntity.setModifiedBy(accessedBy);
			exisUserWorkspaceEntity.setModifiedDate(new Date());
			exisUserWorkspaceEntity.setId(request.getId());
			exisUserWorkspaceEntity.setWorkspaceId(workspaceId);
			exisUserWorkspaceEntity.setSearchId(searchId);
			exisUserWorkspaceEntity.setName(name);
			exisUserWorkspaceEntity.setDescription(description);
			exisUserWorkspaceEntity.setIsActive(isActive);
			exisUserWorkspaceEntity.setIsDelete("N");

			userWorkspaceRepository.save(exisUserWorkspaceEntity);
		}

	}

	@Value("${searchmanagement.service.url}")
	private String searchmanagementUrl;

	@Autowired
	private RestTemplate restTemplate;

	public UserWorkspaceResponse workspaceSearchByUser(Long workspaceId, Long createdBy) {

		UserWorkspaceResponse userWorkspaceResponse = new UserWorkspaceResponse();

		List<UserWorkspace> list = new ArrayList<UserWorkspace>();

		if (null == workspaceId) {

			list = userWorkspaceRepository.findByCreatedByAndIsDeleteOrderByCreatedDateDesc(createdBy, "N");

		} else if (null != workspaceId && null != createdBy) {

			list = userWorkspaceRepository.findByWorkspaceIdAndCreatedByAndIsDeleteOrderByCreatedDateDesc(workspaceId,
					createdBy, "N");
		}

		// Fetch user management Data From User Data
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accessedBy", "" + createdBy);
		headers.add("Authorization", "Basic YXBpLWV4aW13YXRjaDp1ZTg0Q1JSZnRAWGhBMyRG");

		if (!list.isEmpty()) {
			List<SearchQueryWithWS> searchQueryWithWSList = new ArrayList<SearchQueryWithWS>();
			for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
				UserWorkspace userWorkspace = (UserWorkspace) iterator.next();
				SearchQueryWithWS searchQueryWithWS = new SearchQueryWithWS();
				BeanUtils.copyProperties(userWorkspace, searchQueryWithWS);

				String absUrl = searchmanagementUrl + userWorkspace.getSearchId();
				System.out.println("absUrl = " + absUrl);
				ResponseEntity<SearchDetailsResponse> responseEntity = restTemplate.exchange(absUrl, HttpMethod.GET,
						new HttpEntity<Object>(headers), SearchDetailsResponse.class);

				List<SearchDetails> searchDetailsList = responseEntity.getBody().getQueryList();
				if (!searchDetailsList.isEmpty()) {
					SearchDetails searchDetails = searchDetailsList.get(0);

					searchQueryWithWS.setTotalRecords(searchDetails.getTotalRecords());
					searchQueryWithWS.setIsDownloaded(searchDetails.getIsDownloaded());
					if (null != searchDetails.getUserSearchQuery()) {
						searchQueryWithWS.setTradeType(searchDetails.getUserSearchQuery().getTradeType());
						searchQueryWithWS.setFromDate(searchDetails.getUserSearchQuery().getFromDate());
						searchQueryWithWS.setToDate(searchDetails.getUserSearchQuery().getToDate());
						searchQueryWithWS.setCountryCode(searchDetails.getUserSearchQuery().getCountryCode());
					}
				}

				searchQueryWithWSList.add(searchQueryWithWS);
			}

			userWorkspaceResponse.setSearchQueryWithWS(searchQueryWithWSList);
		}

		return userWorkspaceResponse;
	}

	@Value("${usermanagement.service.activelist}")
	private String activelistUrl;

	@Value("${usermanagement.service.update}")
	private String updateurl;

	public void removeSearch(Long userWorkspaceId, Long accessedBy) {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("accessedBy", "" + accessedBy);
			headers.add("Authorization", "Basic YXBpLWV4aW13YXRjaDp1ZTg0Q1JSZnRAWGhBMyRG");

			// TODO Auto-generated method stub
			UserWorkspace userWorkspaceEntity = userWorkspaceRepository.findById(userWorkspaceId).get();
			if (userWorkspaceEntity != null) {
				userWorkspaceEntity.setIsDelete("Y");
				userWorkspaceEntity.setModifiedBy(accessedBy);
				userWorkspaceEntity.setModifiedDate(new Date());
				userWorkspaceRepository.save(userWorkspaceEntity);

				String absUrl = activelistUrl + accessedBy;
				System.out.println("absUrl = " + absUrl);
				/*
				 * ResponseEntity<List<UserSubscription>> responseEntity =
				 * restTemplate.exchange(absUrl, HttpMethod.GET, new
				 * HttpEntity<Object>(headers), new
				 * ParameterizedTypeReference<List<UserSubscription>>() {});
				 */

				ResponseEntity<UserSubscriptionList> responseEntity = restTemplate.exchange(absUrl, HttpMethod.GET,
						new HttpEntity<Object>(headers), UserSubscriptionList.class);

				UserSubscriptionList userSubscriptionList = responseEntity.getBody();
				List<UserSubscriptionDetails> srcList = userSubscriptionList.getUserSubscriptionList();

				if (!srcList.isEmpty()) {
					UserSubscriptionDetails actSubs = srcList.get(0);

					Integer tWs = Integer.parseInt(actSubs.getTotalWorkspace());

					absUrl = updateurl + actSubs.getId();
					System.out.println("absUrl = " + absUrl);

					UserSubscriptionDetailsRequest request = new UserSubscriptionDetailsRequest();
					request.setTotalWorkspace("" + (tWs + 1));

					// restTemplate.put(absUrl,request);

					restTemplate.exchange(absUrl, HttpMethod.PUT, new HttpEntity<>(request, headers), Void.class);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception ---> " + e);
		}

	}

}
