package com.contactManager.dao;

import java.util.List;

import com.contactManager.event.ContactListCriteria;
import com.contactManager.model.Contact;

public interface ContactDao {
	List<Contact> getContacts(ContactListCriteria criteria);

	Contact getContact(long id );

	Contact createContact(Contact contact);

	void deleteContact(Contact contact);

	Contact getContactByEmail(String email);
}
