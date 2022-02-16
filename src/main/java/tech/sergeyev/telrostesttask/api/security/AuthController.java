package tech.sergeyev.telrostesttask.api.security;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.sergeyev.telrostesttask.payload.LoginDto;
import tech.sergeyev.telrostesttask.payload.RegistrationDto;
import tech.sergeyev.telrostesttask.persistence.dao.PersonRepository;
import tech.sergeyev.telrostesttask.persistence.model.Person;
import tech.sergeyev.telrostesttask.service.person.PersonServiceImpl;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    AuthenticationManager authenticationManager;
    PersonServiceImpl personService;
    PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          PersonServiceImpl personService,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto data) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        data.getLogin(),
                        data.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registration(@RequestBody RegistrationDto data) {
        if (personService.existsPersonByLogin(data.getLogin())) {
            return new ResponseEntity<>("Username is already exists", HttpStatus.FORBIDDEN);
        }
        Person user = new Person();
        user.setName(data.getName());
        user.setLastname(data.getLastname());
        user.setPatronymic(data.getPatronymic());
        user.setDateOfBirth(LocalDate.parse(data.getDateOfBirth()));
        user.setEmail(data.getEmail());
        user.setPhone(data.getPhone());
        user.setLogin(data.getLogin());
        user.setPassword(passwordEncoder.encode(data.getPassword()));

        personService.save(user);

        return new ResponseEntity<>("You have successfully registered", HttpStatus.CREATED);
    }
}
