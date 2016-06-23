package com.metlife.model;

public class Insured {

	private Name name;

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Insured [name=" + name + "]";
	}
	
	
	
}
