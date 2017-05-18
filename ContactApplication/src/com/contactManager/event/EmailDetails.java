package com.contactManager.event;

public class EmailDetails {
	private String subject;

	private String message;

	private ContactListCriteria criteria;
	
   public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public ContactListCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(ContactListCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public String toString() {
		return "EmailDetails [subject=" + subject + ", message=" + message + ", criteria=" + criteria + "]";
	}

}
