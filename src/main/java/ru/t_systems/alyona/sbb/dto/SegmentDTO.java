package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.math.BigInteger;
import java.time.Instant;

@Data
public class SegmentDTO {

    private BigInteger id;
    private StationDTO from;
    private StationDTO to;
    private TrainDTO train;
    private Instant departure;
    private Instant arrival;
    private int ticketsLeft;
    private int price;
    private Boolean cancelled;
    private Boolean delayed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SegmentDTO that = (SegmentDTO) o;
        return id.equals(that.id) && from.equals(that.from) &&
                to.equals(that.to) && train.equals(that.train) &&
                departure.equals(that.departure) && arrival.equals(that.arrival) &&
                (ticketsLeft == that.ticketsLeft) && (price == that.price) &&
                cancelled.equals(that.cancelled) && delayed.equals(that.delayed);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (id.hashCode() + from.hashCode() + to.hashCode() +
                train.hashCode() + departure.hashCode() + arrival.hashCode() +
                ticketsLeft + price + cancelled.hashCode() + delayed.hashCode());
    }
}
