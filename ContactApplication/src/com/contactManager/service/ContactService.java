package com.contactManager.service;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.contactManager.event.ContactListCriteria;
import com.contactManager.event.EmailDetails;
import com.contactManager.model.Contact;

public interface ContactService {
	 ResponseEntity< Contact>  getContact(String email);

	ResponseEntity<List<Contact>> getContacts(ContactListCriteria criteria);

	ResponseEntity<Contact> createContact(Contact contact);

	ResponseEntity<Contact> updateContact(String email, Contact contact);

	ResponseEntity<Contact> deleteContact(String email);

	ResponseEntity<List<Contact>> sendEmail(EmailDetails email);
}



