package com.besmartexim.dto.response;

import java.util.List;

public class UserSavedContactsResponse {

	List<SavedContacts> contactList;

	public List<SavedContacts> getContactList() {
		return contactList;
	}

	public void setContactList(List<SavedContacts> contactList) {
		this.contactList = contactList;
	}

}
