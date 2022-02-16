package tech.sergeyev.telrostesttask.payload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationDto {
    String name;
    String lastname;
    String patronymic;
    String dateOfBirth;
    String email;
    String phone;
    String login;
    String password;
}
