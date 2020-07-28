package ru.t_systems.alyona.sbb.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class RegistrationFormDTO {

    private String login;
    private String name;
    private String surname;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;
    private String userPassword;
}
