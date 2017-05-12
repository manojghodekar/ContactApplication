package com.contactManager.service;
import java.util.List;

import com.contactManager.event.ContactListCriteria;
import com.contactManager.event.EmailDetails;
import com.contactManager.model.Contact;

public interface ContactService {
	Contact getContact(String email);

	List<Contact> getContacts(ContactListCriteria criteria);

	Contact createContact(Contact contact);

	Contact updateContact(String email, Contact contact);

	Contact deleteContact(String email);

	List<Contact> sendEmail(EmailDetails email);
}



