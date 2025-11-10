package com.besmartexim.dto.request;

import javax.validation.constraints.NotBlank;

public class WorkspaceRequest {

	@NotBlank
	private String name;

	private String is_active;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

}
