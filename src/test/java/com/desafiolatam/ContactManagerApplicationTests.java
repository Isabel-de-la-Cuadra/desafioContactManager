package com.desafiolatam;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.desafiolatam.models.Contact;
import com.desafiolatam.services.ContactService;

@SpringBootTest
class ContactManagerApplicationTests {
	
	@Test
	void contextLoads() {
	}
	
	@Autowired
	ContactService contactService;
	
	@Test
	public void test_create_contact() {
		Contact contact01 = new Contact("Juanito","Los Palotes", "de la Población", "Vive aquí", "12345678");
		contact01 = contactService.save(contact01);
		assertNotNull(contact01.getId());
	}
	
	@Test
	public void test_list_findAll() {
		List<Contact> listContact = contactService.findAll();
		assertNotNull(listContact);
	}
	
	
	
}