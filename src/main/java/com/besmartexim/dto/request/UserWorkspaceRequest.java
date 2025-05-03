package com.besmartexim.dto.request;

import javax.validation.constraints.NotEmpty;

public class UserWorkspaceRequest {
	private Long id;
	
	@NotEmpty(message = "Please enter workspaceId")
	private Long workspace_id;
	
	private Long search_id;	
	private String name;
	private String description;
	private String is_active;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWorkspace_id() {
		return workspace_id;
	}
	public void setWorkspace_id(Long workspace_id) {
		this.workspace_id = workspace_id;
	}
	public Long getSearch_id() {
		return search_id;
	}
	public void setSearch_id(Long search_id) {
		this.search_id = search_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
		
		
	
	

}
