package ru.t_systems.alyona.sbb.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class RegistrationFormDTO {

    @NotNull(message = "Login can't be empty")
    @Size(min = 3, max = 60, message = "Min login length is 3, max is 60")
    private String login;

    @NotNull(message = "Name can't be empty")
    @Size(min = 2, max = 30, message = "Min name length is 2, max is 30")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "Latin letters, hyphens, spaces are allowed")
    private String name;

    @NotNull(message = "Surname can't be empty")
    @Size(min = 2, max = 30, message = "Min surname length is 2, max is 30")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "Latin letters, hyphens, spaces are allowed")
    private String surname;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Birthday can't be empty")
    @Past(message = "Birthday must be past date")
    private LocalDate birthday;

    @NotNull(message = "Password can't be empty")
    @Size(min = 4, max = 20, message = "Min password length is 4, max is 20")
    private String userPassword;
}
