package tech.sergeyev.telrostesttask.service.person;

import tech.sergeyev.telrostesttask.persistence.model.Person;

public interface PersonService {
    Person getPersonByLogin(String login);
    Person getPersonById(long id);
    Boolean existsPersonByLogin(String login);
    Boolean existsPersonById(long id);
    Person save(Person person);
    void update(Person newPerson);
    void deletePersonById(long id);
}
