package com.contactManager.service;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.contactManager.dao.ContactDao;
import com.contactManager.event.ContactListCriteria;
import com.contactManager.event.EmailDetails;
import com.contactManager.model.Contact;
import com.contactManager.error.ContactException;
import com.contactManager.utility.EmailUtility;

@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService{

	private static final Logger logger = Logger.getLogger(ContactServiceImpl.class.getName());

	@Autowired
	private ContactDao contactDao;

	public ResponseEntity< List<Contact>> getContacts(ContactListCriteria criteria) {
		List<Contact> contactlist = new LinkedList<Contact>();
		try{
			contactlist = contactDao.getContacts(criteria);
			if (contactlist.isEmpty()) {
				throw new ContactException("No Matching contact found wthe give criteria");
			} 
			return new ResponseEntity< List<Contact>>(contactlist,HttpStatus.OK);
		} catch(Exception e){ 
			logger.error("error in getContacts Method" + e);
			return new ResponseEntity<List<Contact>>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public  ResponseEntity< Contact>   getContact(String email) {
		Contact contact = new Contact();
		try{
			contact	= contactDao.getContact(email) ;
			if (contact == null) {
				throw new ContactException ("contact with given email id Does not exist");
			}
			return new ResponseEntity<Contact>(contact,HttpStatus.OK);
		} catch(Exception e){
			logger.error("Error in getContact :"+e);
			return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
		}
	}	

	@Override
	public ResponseEntity<Contact> createContact(Contact contact) {
		try{
			Contact	newContact=contactDao.createContact(contact);
			return new ResponseEntity<Contact>(	newContact,HttpStatus.OK);
		} catch(Exception e){
			logger.error("Error in creating Contact :" + e) ;
			return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Contact> updateContact(String email,Contact contact) {
		try{
			Contact newContact=contactDao.getContact(email);
			if(newContact.getEmailId().equals(contact.getEmailId())){
				newContact.setFirstName(contact.getFirstName());
				newContact.setLastName(contact.getLastName());
				newContact.setInstituteName(contact.getInstituteName());
				newContact.setAddress(contact.getAddress());
				newContact.setCountry(contact.getCountry());
				newContact.setState(contact.getState());
				newContact.setStatus(contact.getStatus());
				contactDao.createContact(newContact);
				return new ResponseEntity<Contact>(	newContact,HttpStatus.OK);
			} else {
			throw new ContactException ("Given Contact can not be update due to different email id");
			}
		} catch(Exception e){
			logger.error("Error in updateContact :" +e) ;
			return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Contact> deleteContact(String email) {
		try{
			Contact contact = contactDao.getContact(email);
			if(contact!=null){
		    contactDao.deleteContact(contact);
			return new ResponseEntity<Contact>(	contact,HttpStatus.OK);
			}else {
				throw new ContactException ("Contact with given email id does not exist");
		    }
		} catch( Exception e){
			logger.error("Error in Delete Contact :" + e);
			return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public  ResponseEntity< List<Contact>> sendEmail(EmailDetails email) {
		try{
			ContactListCriteria criteria = email.getCriteria();
			List<Contact> contactlist  = contactDao.getContacts(criteria);
			
			if(contactlist.isEmpty()){
				throw new ContactException ("No Matching contact found wthe give criteria");
			}
			
			EmailUtility.sendMail(contactlist,email);
			return new ResponseEntity< List<Contact>>(contactlist,HttpStatus.OK);
		} catch( Exception e ){
			logger.error("error in send Email method :" + e);
			return new ResponseEntity<List<Contact>>(HttpStatus.NOT_FOUND);
		}
	} 
}