package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class PassengerDTO {

    private BigInteger id;
    private String name;
    private String surname;
    private LocalDate birthday;

}
