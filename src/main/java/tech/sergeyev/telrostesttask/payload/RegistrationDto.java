package tech.sergeyev.telrostesttask.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(
        level = AccessLevel.PRIVATE,
        makeFinal = true
)
public class RegistrationDto {
    String name;
    String lastname;
    String patronymic;
    @JsonProperty("date_of_birth")
    String dateOfBirth;
    String email;
    String phone;
    String login;
    String password;
}
