package tech.sergeyev.telrostesttask.api.security;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.sergeyev.telrostesttask.payload.LoginDto;
import tech.sergeyev.telrostesttask.payload.RegistrationDto;
import tech.sergeyev.telrostesttask.persistence.model.Person;
import tech.sergeyev.telrostesttask.service.person.PersonServiceImpl;

@RestController
@RequestMapping
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    AuthenticationManager authenticationManager;
    PersonServiceImpl personService;
    ObjectMapper mapper;

    public AuthController(AuthenticationManager authenticationManager,
                          PersonServiceImpl personService,
                          ObjectMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.personService = personService;
        this.mapper = mapper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto data) {
        LOGGER.info("Login request received: username={}", data.getLogin());
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
    public ResponseEntity<?> registration(@RequestBody RegistrationDto data) throws JsonMappingException {
        if (personService.existsPersonByLogin(data.getLogin())) {
            return new ResponseEntity<>("Username is already exists", HttpStatus.FORBIDDEN);
        }
        Person user = new Person();
        mapper.updateValue(user, data);

        personService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
