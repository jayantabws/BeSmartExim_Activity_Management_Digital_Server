package com.besmartexim.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.besmartexim.database.entity.UserDownloadTracker;

public interface UserDownloadTrackerRepo extends JpaRepository<UserDownloadTracker, Long> {

	List<UserDownloadTracker> findByUserId(Long userId);

}
