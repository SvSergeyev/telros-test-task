package tech.sergeyev.telrostesttask.service.person;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.sergeyev.telrostesttask.persistence.dao.PersonRepository;
import tech.sergeyev.telrostesttask.persistence.model.Person;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

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
        return repository.findPersonById(id).orElseThrow();
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
    public void update(Person newPerson) {

    }

    @Override
    public void deletePersonById(long id) {
        repository.deleteById(id);
    }
}
