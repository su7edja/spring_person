package com.spring_person.controller;

import com.spring_person.model.Person;
import com.spring_person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    // Get All Persons
    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // Create a new Person
    @PostMapping("/persons")
    public Person createPerson(@Valid @RequestBody Person person) {
        return personRepository.save(person);
    }

    // Get a Single Person
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personId) {
        Person person = personRepository.findOne(personId);
        if(person == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(person);
    }
 
    // Update a Person
    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long personId,
                                           @Valid @RequestBody Person personDetails) {
        Person person = personRepository.findOne(personId);
        if(person == null) {
            return ResponseEntity.notFound().build();
        }
        person.setFirstName(personDetails.getFirstName());
        person.setLastName(personDetails.getLastName());
        person.setDateOfBirth(personDetails.getDateOfBirth());

        Person updatedPerson = personRepository.save(person);
        return ResponseEntity.ok(updatedPerson);
    }

    // Delete a Person
    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable(value = "id") Long personId) {
                Person person = personRepository.findOne(personId);
        if(person == null) {
            return ResponseEntity.notFound().build();
        }

        personRepository.delete(person);
        return ResponseEntity.ok().build();
    }
}

