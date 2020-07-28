package ru.t_systems.alyona.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private BigInteger id;
    private String login;
    private Boolean isPassenger;
    private PassengerDTO passenger;
    private String password;

}
