package com.besmartexim.dto.response;

import java.util.ArrayList;
import java.util.List;

public class UserWorkspaceResponse {

	List<SearchQueryWithWS> searchQueryWithWS = new ArrayList<SearchQueryWithWS>();

	public List<SearchQueryWithWS> getSearchQueryWithWS() {
		return searchQueryWithWS;
	}

	public void setSearchQueryWithWS(List<SearchQueryWithWS> searchQueryWithWS) {
		this.searchQueryWithWS = searchQueryWithWS;
	}

}
