package com.besmartexim.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.besmartexim.database.entity.Workspace;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

	// public List<Workspace> findByCreatedby(Long created_by);
	List<Workspace> findByCreatedby(Long createdby);

}
