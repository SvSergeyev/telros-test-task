package tech.sergeyev.telrostesttask.service.person;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.sergeyev.telrostesttask.persistence.dao.PersonRepository;
import tech.sergeyev.telrostesttask.persistence.model.Person;

@Service
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
public class PersonServiceImpl implements PersonService {
    static Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
    PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person getPersonByLogin(String login) {
        return repository.findPersonByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User with username \"" + login + "\" not found"
                ));
    }

    @Override
    public Person getPersonById(long id) {
        Person person = repository.findPersonById(id).orElse(null);
        if (person == null) {
            LOGGER.info("Person with id={} not found", id);
        }
        LOGGER.info("Select entity from database: {}", person);
        return person;
    }

    @Override
    public Boolean existsPersonByLogin(String login) {
        return repository.existsByLogin(login);
    }

    @Override
    public Boolean existsPersonById(long id) {
        return repository.existsById(id);
    }

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public void deletePersonById(long id) {
        repository.deleteById(id);
    }
}
