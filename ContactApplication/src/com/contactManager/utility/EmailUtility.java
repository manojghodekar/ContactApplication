package com.contactManager.utility;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.contactManager.event.EmailDetails;
import com.contactManager.model.Contact;
import com.contactManager.service.ContactServiceImpl;

public class EmailUtility {

	private static final Logger logger = Logger.getLogger(EmailUtility.class.getName());
	public static  void sendMail(List<Contact> contactList, EmailDetails email) {
		try{
			Properties properties = new Properties();
			properties.load(ContactServiceImpl.class.getResourceAsStream("/resources/mail.properties"));
			String username = properties.getProperty("mail.smtp.username");
			String password = properties.getProperty("mail.smtp.password");
			Session session = Session.getDefaultInstance( properties,new javax.mail.Authenticator() {    
				protected PasswordAuthentication getPasswordAuthentication() {    
					return new PasswordAuthentication(username,password);  
				} });  

			for(Contact contact:contactList){
				MimeMessage message = new MimeMessage(session);
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(contact.getEmailId()));
				message.setSubject(email.getSubject());
				message.setText(email.getSubject());
				Transport.send(message);
				logger.info("Message send Successfully To :" + contact.getEmailId());
			} 
		} catch(MessagingException | IOException ex) {
			logger.error("Error in sendMail method:" + ex);
		} 
	}
}
