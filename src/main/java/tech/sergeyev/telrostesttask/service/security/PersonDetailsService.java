package tech.sergeyev.telrostesttask.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.sergeyev.telrostesttask.persistence.model.Person;
import tech.sergeyev.telrostesttask.service.person.PersonServiceImpl;

import java.util.Collections;
import java.util.List;

@Service
public class PersonDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDetailsService.class);

    private final PersonServiceImpl service;

    public PersonDetailsService(PersonServiceImpl service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = service.getPersonByLogin(username);
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("default"));
        return new User(person.getLogin(), person.getPassword(), authorities);
    }
}
