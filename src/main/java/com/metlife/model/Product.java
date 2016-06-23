package com.metlife.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

	private String typeCode;
	private String nameCode;
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getNameCode() {
		return nameCode;
	}
	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}
	@Override
	public String toString() {
		return "Product [typeCode=" + typeCode + ", nameCode=" + nameCode + "]";
	}
	
	
	
}
