package com.besmartexim.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.besmartexim.database.entity.Workspace;
import com.besmartexim.database.repository.WorkspaceRepository;
import com.besmartexim.dto.request.WorkspaceRequest;
import com.besmartexim.dto.response.WSpace;
import com.besmartexim.dto.response.WorkspaceResponse;


@Service
public class WorkspaceService {
	
	@Autowired
	private WorkspaceRepository workspaceRepository;
	
	public Long workspaceCreate (WorkspaceRequest request, Long accessedBy) throws Exception{
		
		String name = request.getName();		
		String is_active = request.getIs_active();
		
		Workspace workspaceEntity = new Workspace();
		workspaceEntity.setName(name);
		workspaceEntity.setIs_active(is_active);
		workspaceEntity.setIs_delete("N");
		workspaceEntity.setCreatedby(accessedBy);
		workspaceEntity.setCreated_date(new Date());
		workspaceRepository.save(workspaceEntity);
		return workspaceEntity.getId();
	}
	
	public void workspaceUpdate (WorkspaceRequest request, Long workspaceId, Long accessedBy) throws Exception{
			
		Optional<Workspace> list = workspaceRepository.findById(workspaceId);
		Workspace workspaceEntity = list.get();
		
		String name = request.getName();		
		String is_active = request.getIs_active();
		
		
		workspaceEntity.setName(name);
		workspaceEntity.setIs_active(is_active);
		workspaceEntity.setIs_delete("N");
		workspaceEntity.setModified_by(accessedBy);
		workspaceEntity.setModified_date(new Date());
		workspaceRepository.save(workspaceEntity);
	}
	
	public WorkspaceResponse workspaceListByUserId(Long userId) throws Exception{	
		
		WorkspaceResponse workspaceResponse = new WorkspaceResponse();
		
		List<Workspace> srclist = workspaceRepository.findByCreatedby(userId);
		
		List<WSpace> targetList = new ArrayList<WSpace>();
		
		if(null!=srclist && !srclist.isEmpty()) {
			
			for(Workspace workspace:srclist) {
				WSpace  wSpace= new WSpace();
				BeanUtils.copyProperties(workspace, wSpace);		
				targetList.add(wSpace);
			}
		}
		
		workspaceResponse.setWorkspaceList(targetList);
		
		return workspaceResponse;
			
	}
	

}
