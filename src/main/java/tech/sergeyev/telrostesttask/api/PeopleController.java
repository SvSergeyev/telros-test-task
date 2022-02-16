package tech.sergeyev.telrostesttask.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.sergeyev.telrostesttask.persistence.model.Person;
import tech.sergeyev.telrostesttask.service.person.PersonServiceImpl;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PersonServiceImpl personService;

    public PeopleController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPerson(@PathVariable long id) {
        return new ResponseEntity<>(
                personService.getPersonById(id),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable long id, Person person) {
        if (!personService.existsPersonById(id)) {
            return ResponseEntity.notFound().build();
        }
        person.setId(id);
        personService.update(person);
        return ResponseEntity.ok("Updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable long id) {
        if (!personService.existsPersonById(id)) {
            return ResponseEntity.notFound().build();
        }
        personService.deletePersonById(id);
        return ResponseEntity.ok("Person has been removed");
    }
}
