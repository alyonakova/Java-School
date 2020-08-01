package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

@Data
public class ChangeUserDataDTO {

    private String login;
    private String password;
    private String newLogin;
    private String newPassword;
}
