package com.besmartexim.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.besmartexim.database.entity.UserSavedContacts;
import com.besmartexim.database.repository.UserSavedContactsRepository;
import com.besmartexim.dto.request.UserSavedContactsRequest;
import com.besmartexim.dto.response.SavedContacts;
import com.besmartexim.dto.response.UserDetailsResponse;
import com.besmartexim.dto.response.UserSavedContactsResponse;

@Service
public class UserSavedContactsService {

	@Autowired
	private UserSavedContactsRepository userSavedContactsRepository;
	
	@Value("${usermanagement.service.userdetails}")
	private String userDetailsUrl;
	
	@Autowired
	private RestTemplate restTemplate;

	public String savecontact(UserSavedContactsRequest request, Long accessedBy) {
		String address = null;
		String email = null;
		String mobile = null;
		String website = null;

		List<UserSavedContacts> list = userSavedContactsRepository.findBycreatedByAndCompanyName(accessedBy,
				request.getCompanyName());

		if (list.isEmpty()) {
			String companyName = request.getCompanyName();

			List<UserSavedContacts> existingList = userSavedContactsRepository.findByCompanyName(companyName);

			if (!existingList.isEmpty()) {
				for (Iterator<?> iterator = existingList.iterator(); iterator.hasNext();) {
					UserSavedContacts userSavedContacts = (UserSavedContacts) iterator.next();
					if (userSavedContacts.getAddress() != null && userSavedContacts.getEmail() != null
							&& userSavedContacts.getMobile() != null && userSavedContacts.getWebsite() != null) {

						address = userSavedContacts.getAddress();
						email = userSavedContacts.getEmail();
						mobile = userSavedContacts.getMobile();
						website = userSavedContacts.getWebsite();
						break;
					}
				}
			}

			UserSavedContacts userSavedContactsEntity = new UserSavedContacts();
			userSavedContactsEntity.setCompanyName(companyName);
			userSavedContactsEntity.setAddress(address);
			userSavedContactsEntity.setEmail(email);
			userSavedContactsEntity.setMobile(mobile);
			userSavedContactsEntity.setWebsite(website);
			userSavedContactsEntity.setCreatedBy(accessedBy);
			userSavedContactsEntity.setCreatedDate(new Date());
			userSavedContactsRepository.save(userSavedContactsEntity);
			return "CREATED";
		} else {
			return "DUPLICATE";
		}
	}

	public void updatecontact(UserSavedContactsRequest request, Long contactId, Long accessedBy) {

		Optional<UserSavedContacts> list = userSavedContactsRepository.findById(contactId);

		String companyName = request.getCompanyName();
		String address = request.getAddress();
		String email = request.getEmail();
		String mobile = request.getMobile();
		String website = request.getWebsite();

		UserSavedContacts userSavedContactsEntity = list.get();
		userSavedContactsEntity.setCompanyName(companyName);
		userSavedContactsEntity.setAddress(address);
		userSavedContactsEntity.setEmail(email);
		userSavedContactsEntity.setMobile(mobile);
		userSavedContactsEntity.setWebsite(website);
		userSavedContactsEntity.setModifiedBy(accessedBy);
		userSavedContactsEntity.setModifiedDate(new Date());
		userSavedContactsRepository.save(userSavedContactsEntity);
	}

	public UserSavedContactsResponse savedContactListByUserId(Long userId, Long accessedBy) throws Exception {
		UserSavedContactsResponse userSavedContactsResponse = new UserSavedContactsResponse();

		List<UserSavedContacts> srclist = userSavedContactsRepository.findBycreatedBy(userId);

		List<SavedContacts> targetlist = new ArrayList<SavedContacts>();

		if (null != srclist && !srclist.isEmpty()) {

			for (UserSavedContacts UserSavedContacts : srclist) {
				SavedContacts savedContacts = new SavedContacts();
				BeanUtils.copyProperties(UserSavedContacts, savedContacts);
				savedContacts = getCreatorDetails(savedContacts, UserSavedContacts.getCreatedBy(), accessedBy);
				targetlist.add(savedContacts);
			}

		}

		userSavedContactsResponse.setContactList(targetlist);
		return userSavedContactsResponse;

	}

	public UserSavedContactsResponse savedContactListAll(Long accessedBy) throws Exception {
		UserSavedContactsResponse userSavedContactsResponse = new UserSavedContactsResponse();

		List<UserSavedContacts> srclist = userSavedContactsRepository.findAll();

		List<SavedContacts> targetlist = new ArrayList<SavedContacts>();

		if (null != srclist && !srclist.isEmpty()) {

			for (UserSavedContacts UserSavedContacts : srclist) {
				SavedContacts savedContacts = new SavedContacts();
				BeanUtils.copyProperties(UserSavedContacts, savedContacts);
				savedContacts = getCreatorDetails(savedContacts, UserSavedContacts.getCreatedBy(), accessedBy);
				targetlist.add(savedContacts);
			}

		}

		userSavedContactsResponse.setContactList(targetlist);
		return userSavedContactsResponse;

	}
	
	
	private SavedContacts getCreatorDetails(SavedContacts savedContacts,Long createdBy, Long accessedBy) {
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("accessedBy", "" + accessedBy);
			headers.add("Authorization", "Basic YXBpLWV4aW13YXRjaDp1ZTg0Q1JSZnRAWGhBMyRG");
			
			String uriString = UriComponentsBuilder.fromHttpUrl(userDetailsUrl)
				    .queryParam("userId", createdBy)
				    .build().toUriString();
			
			ResponseEntity<UserDetailsResponse> responseEntity = restTemplate.exchange(uriString, HttpMethod.GET,
					new HttpEntity<Object>(headers), UserDetailsResponse.class);
			
			UserDetailsResponse response = responseEntity.getBody();
			//System.out.println(response);
			savedContacts.setCreatorEmail(response.getEmail());
			savedContacts.setCreatorCompany(response.getCompanyName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return savedContacts;
	}

}
