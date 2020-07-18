package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;

@Data
public class PassengerDTO {

    private BigInteger id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private Collection<TicketDTO> tickets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerDTO that = (PassengerDTO) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                surname.equals(that.surname) &&
                birthday.equals(that.birthday);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (id.hashCode() + name.hashCode() + surname.hashCode() + birthday.hashCode());
    }

}
