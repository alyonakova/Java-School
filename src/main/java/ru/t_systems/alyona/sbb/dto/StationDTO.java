package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.math.BigInteger;
import java.time.ZoneId;

@Data
public class StationDTO {

    private BigInteger id;
    private String name;
    private ZoneId zoneId;

}
