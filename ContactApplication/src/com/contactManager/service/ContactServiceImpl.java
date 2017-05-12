package com.contactManager.service;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contactManager.dao.ContactDao;
import com.contactManager.event.ContactListCriteria;
import com.contactManager.event.EmailDetails;
import com.contactManager.model.Contact;

@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService{

	private static final Logger logger = Logger.getLogger(ContactServiceImpl.class.getName());

	@Autowired
	private ContactDao contactDao;

	public List<Contact> getContacts(ContactListCriteria criteria) {
		try{
			return contactDao.getContacts(criteria);
		} catch(Exception e){
			logger.error("error in getContacts Method");
			return null;
		}
	}

	@Override
	public Contact getContact(String email) {
		try{
			return contactDao.getContact(email) ;
		} catch(Exception e){
			logger.error("error in getContact Method");
			return null;
		}
	}	

	@Override
	public Contact createContact(Contact contact) {
		try{
			contactDao.createContact(contact);
			return contact;
		} catch(Exception e){
			logger.error("error in crateContact Method");
			return null;
		}
	}

	@Override
	public Contact updateContact(String email,Contact contact) {
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
			return newContact;
			}else{
				logger.info(" Contact cannot be updated");
				return null;
			}
		} catch(Exception e){
			logger.error("error in updateContact");
			return null;
		}
	}

	@Override
	public Contact deleteContact(String email) {
		try{
			Contact contact=contactDao.getContact(email);
			contactDao.deleteContact(email);
			return contact;
		}catch(Exception e){
			logger.error("Contact Doesnt Exist");
			return null;
		}
	}

	@Override
	public  List<Contact> sendEmail(EmailDetails email) {
		try{
			ContactListCriteria criteria=new ContactListCriteria(email.getInstituteName(),email.getCountry(),email.getState(),email.getStatus());
			List<Contact> contactlist=contactDao.getContacts(criteria);
			sendMail(contactlist,email);
			return contactlist;
		}catch(Exception e){
			logger.error("error in send mail method");
			return null;
		}
	} 

	private void sendMail(List<Contact> contactList,EmailDetails email) {
		try{
			Properties properties=new Properties();
			properties.load(ContactServiceImpl.class.getResourceAsStream("/resources/mail.properties"));
			String username = properties.getProperty("mail.smtp.username");
			String password = properties.getProperty("mail.smtp.password");
			Session session=Session.getDefaultInstance( properties,new javax.mail.Authenticator() {    
				protected PasswordAuthentication getPasswordAuthentication() {    
					return new PasswordAuthentication(username,password);  
				} });  
			
			for(Contact contact:contactList){
				MimeMessage message=new MimeMessage(session);
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(contact.getEmailId()));
				message.setSubject(email.getSubject());
				message.setText(email.getSubject());
				Transport.send(message);
				logger.info("Message send Successfully");
			}
		}catch(Exception e){
			logger.error("Error in sendMail method");
		}
	}
}
