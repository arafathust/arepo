package com.metlife.model;

import java.util.ArrayList;

public class ClaimExtensionTermLife {

	private Insured insured;
	private ArrayList<Rider> riders;
	
	
	
	public Insured getInsured() {
		return insured;
	}



	public void setInsured(Insured insured) {
		this.insured = insured;
	}



	public ArrayList<Rider> getRiders() {
		return riders;
	}



	public void setRiders(ArrayList<Rider> riders) {
		this.riders = riders;
	}



	@Override
	public String toString() {
		return "ClaimExtensionTermLife [insured=" + insured + "]";
	}
	
	
	
}
