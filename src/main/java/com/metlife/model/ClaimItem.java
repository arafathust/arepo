package com.metlife.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClaimItem {

	private Claim item;

	public Claim getItem() {
		return item;
	}

	public void setItem(Claim item) {
		this.item = item;
	}
	
	
	
}
