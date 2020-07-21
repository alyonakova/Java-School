package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class StationDTO {

    private BigInteger id;
    private String name;
    private Integer zoneId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationDTO that = (StationDTO) o;
        return id.equals(that.id) &&
                name.equals(that.name) && (zoneId.equals(that.zoneId));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (id.hashCode() + name.hashCode() + zoneId.hashCode());
    }
}
