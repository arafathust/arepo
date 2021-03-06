package com.metlife.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {

	private String suffix;
	private String firstName;
	private String lastName;
	private String middleName;
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	@Override
	public String toString() {
		return "Name [suffix=" + suffix + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + "]";
	}
	
	
	
}
