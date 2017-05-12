package com.contactManager.event;

public class ContactListCriteria {

	private String instituteName;

	private String country;

	private String state;

	private String status;

	public ContactListCriteria() {
		super();
	}

	public ContactListCriteria(String instituteName, String country, String state, String status) {
		super();
		System.out.println("in Parameterized Constructor");
		this.instituteName = instituteName;
		this.country = country;
		this.state = state;
		this.status = status;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
