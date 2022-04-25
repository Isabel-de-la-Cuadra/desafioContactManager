package com.desafiolatam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiolatam.models.Contact;
import com.desafiolatam.repositories.ContactRepository;

@Service
public class ContactService {

	@Autowired
	ContactRepository contactRepository;
	
	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}
	
	public void deleteById(Long id) {
		contactRepository.deleteById(id);
	}
	
	public List<Contact>findAll(){
		return contactRepository.findAll();
	}
}
