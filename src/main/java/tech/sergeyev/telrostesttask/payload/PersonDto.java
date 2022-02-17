package tech.sergeyev.telrostesttask.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Optional;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto {
    @JsonProperty("name")
    Optional<String> name;

    @JsonProperty("lastname")
    Optional<String> lastname;

    @JsonProperty("patronymic")
    Optional<String> patronymic;

    @JsonProperty("date_of_birth")
    Optional<LocalDate> dateOfBirth;

    @JsonProperty("email")
    Optional<String> email;

    @JsonProperty("phone")
    Optional<String> phone;

    @JsonProperty("login")
    Optional<String> login;

    @JsonProperty("password")
    Optional<String> password;
}
