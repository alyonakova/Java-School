package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class TicketDTO {

    private BigInteger id;
    private PassengerDTO passenger;
    private List<SegmentDTO> segments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDTO that = (TicketDTO) o;
        return id.equals(that.id) &&
                passenger.equals(that.passenger) &&
                segments.equals(that.segments);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (id.hashCode() + passenger.hashCode() + segments.size());
    }

}
