package com.contactManager.service;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.contactManager.dao.ContactDao;
import com.contactManager.event.ContactListCriteria;
import com.contactManager.event.EmailDetails;
import com.contactManager.model.Contact;
import com.contactManager.utility.ContactException;
import com.contactManager.utility.EmailUtility;

@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService{

	private static final Logger logger = Logger.getLogger(ContactServiceImpl.class.getName());

	@Autowired
	private ContactDao contactDao;

	public List<Contact> getContacts(ContactListCriteria criteria) {
		List<Contact> contactlist = new LinkedList<Contact>();
		try{
			contactlist = contactDao.getContacts(criteria);
			if (contactlist.isEmpty()) {
				throw new ContactException("No Match Found");
			} 
		} catch(Exception e){ 
			logger.error("error in getContacts Method" + e);
		}
		return contactlist;
	}

	@Override
	public Contact getContact(String email) {
		Contact contact = null;
		try{
			contact	= contactDao.getContact(email) ;
			if (contact == null) {
				throw new ContactException ("contact with given email id Does not exist");
			}
		} catch(Exception e){
			logger.error("error in getContact Method :" +e );
		}
		return contact;
	}	

	@Override
	public Contact createContact(Contact contact) {
		Contact newContact = null;
		try{
			newContact=contactDao.createContact(contact);
		} catch(Exception e){
			logger.error("Error in creating Contact :" + e) ;
		}
		return newContact;
	}

	@Override
	public Contact updateContact(String email,Contact contact) {
		Contact newContact=null;
		try{
			newContact=contactDao.getContact(email);
			if(newContact.getEmailId().equals(contact.getEmailId())){
				newContact.setFirstName(contact.getFirstName());
				newContact.setLastName(contact.getLastName());
				newContact.setInstituteName(contact.getInstituteName());
				newContact.setAddress(contact.getAddress());
				newContact.setCountry(contact.getCountry());
				newContact.setState(contact.getState());
				newContact.setStatus(contact.getStatus());
				contactDao.createContact(newContact);
			} else {
				throw new ContactException ("Given Contact can not be update due to different email id");
			}
		} catch(Exception e){
			logger.error("Error in updateContact :" +e) ;
		}
		return newContact;
	}

	@Override
	public Contact deleteContact(String email) {
		Contact contact = null;
		try{
			contact = contactDao.deleteContact(email);
		} catch (IllegalArgumentException e){
			logger.error("Illegal Argument to deleteContact Method " + e);
		}
		catch( Exception e){
			logger.error("Error in Delete Contact :" + e);
		}
		return contact;
	}

	@Override
	public  List<Contact> sendEmail(EmailDetails email) {
		List<Contact> contactlist = new LinkedList<Contact>();
		try{
			ContactListCriteria criteria = email.getCriteria();
			contactlist = contactDao.getContacts(criteria);
			if(contactlist.isEmpty()){
				throw new ContactException ("No contact found wthe give criteria");
			}
		  EmailUtility.sendMail(contactlist,email);
		} catch( Exception e ){
			logger.error("error in send Email method :" + e);
		}
		return contactlist;
	} 
}
