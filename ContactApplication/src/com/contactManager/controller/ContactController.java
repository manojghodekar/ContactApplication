package com.contactManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.contactManager.event.ContactListCriteria;
import com.contactManager.event.EmailDetails;
import com.contactManager.model.Contact;
import com.contactManager.service.ContactService;

@RestController
@RequestMapping("contacts")
public class ContactController 
{
	@Autowired
	private ContactService contactService;

	@RequestMapping(method = RequestMethod.GET, produces ="application/json")
	@ResponseBody
	public ResponseEntity< List <Contact> > getContacts(@RequestParam(value="instituteName",required=false) String instituteName,
			@RequestParam(value="country", required=false) String country,
			@RequestParam(value="state", required=false) String state,
			@RequestParam(value="status",required=false) String status ) {
		ContactListCriteria criteria = new ContactListCriteria(instituteName,country,state,status);
		return contactService.getContacts(criteria);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/{email}", produces = "application/json" )
	@ResponseBody
	public  ResponseEntity<Contact> getContact(@PathVariable String email){
		return contactService.getContact(email);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json" )
	@ResponseBody
	public ResponseEntity <Contact> createContact(@RequestBody Contact contact) {
		return contactService.createContact(contact);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{email}", produces = "application/json")
	@ResponseBody
	public ResponseEntity <Contact> updateContact(@PathVariable String email, @RequestBody Contact contact){
		return contactService.updateContact(email,contact);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{email}", produces = "application/json")
	@ResponseBody
	public ResponseEntity< Contact > deleteContact(@PathVariable String email){
		return contactService.deleteContact(email);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/send-mail", produces = "application/json")
	@ResponseBody
	public  ResponseEntity< List<Contact> > sendEmail(@RequestBody EmailDetails email) { 
		return contactService.sendEmail(email);
	}
}