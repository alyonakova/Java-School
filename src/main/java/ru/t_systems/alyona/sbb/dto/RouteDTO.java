package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.Collection;

@Data
public class RouteDTO {

    private BigInteger id;
    private StationDTO from;
    private StationDTO to;
    private Collection<SegmentDTO> segments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteDTO that = (RouteDTO) o;
        return id.equals(that.id) &&
                from.equals(that.from) &&
                to.equals(that.to) &&
                segments.equals(that.segments);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (id.hashCode() + from.hashCode() + to.hashCode() + segments.size());
    }

}
