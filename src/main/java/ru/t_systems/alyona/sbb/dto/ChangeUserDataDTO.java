package ru.t_systems.alyona.sbb.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class ChangeUserDataDTO {

    private BigInteger id;
    private String login;
    private String password;
    private String newLogin;
    private String newPassword;
    private String newName;
    private String newSurname;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate newBirthday;
}
