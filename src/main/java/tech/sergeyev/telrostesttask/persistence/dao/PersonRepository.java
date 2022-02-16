package tech.sergeyev.telrostesttask.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.sergeyev.telrostesttask.persistence.model.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findPersonByLogin(String login);
    Optional<Person> findPersonById(long id);
    Boolean existsByLogin(String login);
    Boolean existsById(long id);
}
