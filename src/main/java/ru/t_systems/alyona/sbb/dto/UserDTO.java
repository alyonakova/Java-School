package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserDTO {

    private BigInteger id;
    private String login;
    private Boolean isPassenger;
    private PassengerDTO passenger;

}
