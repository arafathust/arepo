package com.metlife.model;

public class Rider {
	
	private String typeCode;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Override
	public String toString() {
		return "Rider [typeCode=" + typeCode + "]";
	}
	
	

}
