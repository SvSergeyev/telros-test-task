package tech.sergeyev.telrostesttask.api;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.sergeyev.telrostesttask.payload.PersonDto;
import tech.sergeyev.telrostesttask.persistence.model.Person;
import tech.sergeyev.telrostesttask.service.person.PersonServiceImpl;

@RestController
@RequestMapping("/people")
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true)
public class PeopleController {
    static Logger LOGGER = LoggerFactory.getLogger(PeopleController.class);
    PersonServiceImpl personService;
    ObjectMapper mapper;

    public PeopleController(PersonServiceImpl personService,
                            ObjectMapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPerson(@PathVariable long id) {
        LOGGER.info("Get request received: id={}", id);

        Person person = personService.getPersonById(id);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePerson(
            @PathVariable long id,
            @RequestBody PersonDto updatePersonRequest
    ) throws JsonMappingException {
        LOGGER.info("Update request received: id={}, {}", id, updatePersonRequest);
        Person person = personService.getPersonById(id);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        mapper.updateValue(person, updatePersonRequest);
        personService.save(person);
        LOGGER.info("Entity updated: {}", person);

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable long id) {
        LOGGER.info("Delete request received: id={}", id);
        if (!personService.existsPersonById(id)) {
            return ResponseEntity.notFound().build();
        }
        personService.deletePersonById(id);
        LOGGER.info("Person with id={} has been removed", id);
        return ResponseEntity.ok("Person has been removed");
    }
}
