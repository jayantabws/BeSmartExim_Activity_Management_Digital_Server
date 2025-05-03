package com.besmartexim.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.besmartexim.database.entity.UserSavedContacts;

@Repository
public interface UserSavedContactsRepository extends JpaRepository<UserSavedContacts, Long>{

	List<UserSavedContacts> findBycreatedBy(Long createdby);
	List<UserSavedContacts> findBycreatedByAndCompanyName(Long createdby, String comName);
	List<UserSavedContacts> findByCompanyName(String companyName);

}
