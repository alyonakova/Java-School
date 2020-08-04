package ru.t_systems.alyona.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private BigInteger id;

    @NotNull(message = "Login can't be empty")
    @Size(min = 3, max = 60, message = "Min login length is 3, max is 60")
    private String login;

    private Boolean isPassenger;
    private PassengerDTO passenger;

    @NotNull(message = "Password can't be empty")
    @Size(min = 4, max = 20, message = "Min password length is 4, max is 20")
    private String password;

}
