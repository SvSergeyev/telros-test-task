package tech.sergeyev.telrostesttask.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@FieldDefaults(
        level = AccessLevel.PRIVATE
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto {
    @JsonProperty("name")
    String name;

    @JsonProperty("lastname")
    String lastname;

    @JsonProperty("patronymic")
    String patronymic;

    @JsonProperty("date_of_birth")
    LocalDate dateOfBirth;

    @JsonProperty("email")
    String email;

    @JsonProperty("phone")
    String phone;

    @JsonProperty("login")
    String login;

    @JsonProperty("password")
    @Setter
    String password;
}
