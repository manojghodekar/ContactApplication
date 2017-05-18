package com.contactManager.dao;

import java.util.List;

import com.contactManager.event.ContactListCriteria;
import com.contactManager.model.Contact;

public interface ContactDao {
	List<Contact> getContacts(ContactListCriteria criteria);

	Contact getContact(String email);

	Contact createContact(Contact contact);

	Contact deleteContact(String email);
}
