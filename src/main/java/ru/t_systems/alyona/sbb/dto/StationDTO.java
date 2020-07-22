package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class StationDTO {

    private BigInteger id;
    private String name;
    private Integer zoneId;

}
