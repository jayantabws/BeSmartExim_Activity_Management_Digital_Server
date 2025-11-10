package com.besmartexim.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.besmartexim.database.entity.UserWorkspace;

@Repository
public interface UserWorkspaceRepository extends JpaRepository<UserWorkspace, Long> {

	List<UserWorkspace> findByCreatedByAndIsDeleteOrderByCreatedDateDesc(Long createdby, String isDelete);

	List<UserWorkspace> findByWorkspaceIdAndCreatedByAndIsDeleteOrderByCreatedDateDesc(Long workspaceId, Long createdby,
			String isDelete);

}
