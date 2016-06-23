package com.metlife.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Claims {

	private ArrayList<ClaimItem> items;

	public ArrayList<ClaimItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<ClaimItem> items) {
		this.items = items;
	}
	
	
	
}
