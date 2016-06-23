package com.metlife.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Claim {

	private String number;
	private String self;
	private String accountNumber;
	private Product product;
	private String submitDate;
	private String statusCode;
	private Amount claimedAmount;
	private ClaimExtensionTermLife extension;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Amount getClaimedAmount() {
		return claimedAmount;
	}
	public void setClaimedAmount(Amount claimedAmount) {
		this.claimedAmount = claimedAmount;
	}
	
	public ClaimExtensionTermLife getExtension() {
		return extension;
	}
	public void setExtension(ClaimExtensionTermLife extension) {
		this.extension = extension;
	}
	@Override
	public String toString() {
		return "Claim [number=" + number + ", self=" + self + ", accountNumber=" + accountNumber + ", product="
				+ product + ", submitDate=" + submitDate + ", statusCode=" + statusCode + ", claimedAmount="
				+ claimedAmount + ", extension=" + extension + "]";
	}
	
	
	
	
	
}
