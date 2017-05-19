package com.contactManager.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.contactManager.event.ContactListCriteria;
import com.contactManager.model.Contact;

@Repository("contactDao")
public class ContactDaoImpl implements ContactDao {


	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Contact> getContacts(ContactListCriteria criteria) {

		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Contact.class);
		if (criteria.getCountry() != null && (!criteria.getCountry().isEmpty()))	{
			crit.add(Restrictions.like("country",criteria.getCountry() ));
		}
		if (criteria.getState() != null && (!criteria.getState().isEmpty())){
			crit.add(Restrictions.like("state",criteria.getState() ));
		}
		if (criteria.getInstituteName() != null && (!criteria.getInstituteName().isEmpty())){
			crit.add(Restrictions.like("instituteName",criteria.getInstituteName()));
		}
		if (criteria.getStatus() != null && (!criteria.getStatus().isEmpty())){
			crit.add(Restrictions.like("status",criteria.getStatus() ));
		}

		return crit.list();
	}

	@Override
	public Contact getContact(String email) {
		return  (Contact) sessionFactory.getCurrentSession().get(Contact.class,email);
	}

	@Override
	public Contact createContact(Contact contact) {
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		return contact;
	}

	@Override
	public void deleteContact(Contact contact) {
		sessionFactory.getCurrentSession().delete(contact);
	}
}