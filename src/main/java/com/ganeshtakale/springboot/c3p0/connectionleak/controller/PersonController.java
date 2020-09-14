package com.ganeshtakale.springboot.c3p0.connectionleak.controller;

import com.ganeshtakale.springboot.c3p0.connectionleak.model.Person;
import com.ganeshtakale.springboot.c3p0.connectionleak.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/person") public class PersonController {

	private PersonService service;

	public PersonController(PersonService service) {
		this.service = service;
	}

	@GetMapping("/all") public List<Person> getAll() {
		// hibernate connection leak check only work with session

		return service.getAll();

		// jdbc connection does not work with c3p0 connection leak
		// to test uncomment below code
		// return service.getAllPerson();
	}
}
