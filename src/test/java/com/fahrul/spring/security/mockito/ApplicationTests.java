package com.fahrul.spring.security.mockito;


import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fahrul.spring.security.mockito.entity.Person;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ApplicationTests {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private TestRestTemplate template;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}
	
	@Test
	@WithMockUser("/fahrul-1")
	public void testSavePerson() throws Exception{
		Person person =new Person(0,"fahrul","IT");
		String jsonRquest = mapper.writeValueAsString(person);
		
		MvcResult result = mockMvc.perform(post("/savePerson").content(jsonRquest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@WithMockUser("/fahrul-1")
	@Test
	public void testgetPerson() throws Exception{
		MvcResult result = mockMvc.perform(get("/getAllPersons").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		
		assertEquals(200, result.getResponse().getStatus());
	}
	@Test
	public void testgetPersonsWithTestRestTemplate() {
		ResponseEntity<?> response = template.withBasicAuth("fahrul", "pwd").getForEntity("/getAllPersons", ArrayList.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	

}
