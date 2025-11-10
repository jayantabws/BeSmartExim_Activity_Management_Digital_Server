package com.besmartexim.dto.request;

import java.io.Serializable;

public enum SearchType implements Serializable {
	ADVANCE, TRADE, IN_DEPTH, HISTORY, MACRO;

	public String getValue() {
		return this.name();
	}
}
