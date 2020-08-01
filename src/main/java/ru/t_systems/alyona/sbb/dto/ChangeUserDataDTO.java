package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ChangeUserDataDTO {

    private String login;
    private String password;
    private String newLogin;
    private String newPassword;
    private String newName;
    private String newSurname;
    private LocalDate newBirthday;
}
