package com.fahrul.spring.security.mockito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fahrul.spring.security.mockito.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
