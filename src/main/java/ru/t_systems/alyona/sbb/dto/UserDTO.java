package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserDTO {

    private BigInteger id;
    private String login;
    private Boolean isPassenger;
    private PassengerDTO passenger;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO that = (UserDTO) o;
        return id.equals(that.id) &&
                login.equals(that.login) &&
                isPassenger.equals(that.isPassenger) &&
                passenger.equals(that.passenger);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (id.hashCode() + login.hashCode() + isPassenger.hashCode() + passenger.hashCode());
    }

}
