package com.fahrul.spring.security.mockito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fahrul.spring.security.mockito.entity.Person;
import com.fahrul.spring.security.mockito.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;

	public Person savePerson(Person person) {
		System.out.println("Service savePerson() method Called...");
		return repository.save(person);
	}
	
	public List<Person> findAllPersons() {
		System.out.println("Service findAllPersons() method Called...");
		return repository.findAll();
	}

}
