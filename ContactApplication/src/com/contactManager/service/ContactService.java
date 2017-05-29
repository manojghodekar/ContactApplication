package com.contactManager.service;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.contactManager.event.ContactListCriteria;
import com.contactManager.event.EmailDetails;
import com.contactManager.model.Contact;

public interface ContactService {
	 ResponseEntity< Contact>  getContact(long id);

	ResponseEntity<List<Contact>> getContacts(ContactListCriteria criteria);

	ResponseEntity<Contact> createContact(Contact contact);

	ResponseEntity<Contact> updateContact(long id, Contact contact);

	ResponseEntity<Contact> deleteContact(long id);

	ResponseEntity<List<Contact>> sendEmail(EmailDetails email);
}



