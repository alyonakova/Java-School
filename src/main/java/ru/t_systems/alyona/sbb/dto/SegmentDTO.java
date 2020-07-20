package ru.t_systems.alyona.sbb.dto;

import lombok.Data;
import ru.t_systems.alyona.sbb.entity.RoutePO;
import ru.t_systems.alyona.sbb.entity.TicketPO;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

@Data
public class SegmentDTO {

    private BigInteger id;
    private StationDTO from;
    private StationDTO to;
    private TrainDTO train;
    private Calendar departure;
    private Calendar arrival;
    private int ticketsLeft;
    private int price;
    private Boolean cancelled;
    private Boolean delayed;
    private List<TicketDTO> tickets;
    private List<RouteDTO> routes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SegmentDTO that = (SegmentDTO) o;
        return id.equals(that.id) &&
                from.equals(that.from) &&
                to.equals(that.to) &&
                train.equals(that.train) &&
                departure.equals(that.departure) &&
                arrival.equals(that.arrival) &&
                (ticketsLeft == that.ticketsLeft) &&
                (price == that.price) &&
                cancelled.equals(that.cancelled) &&
                delayed.equals(that.delayed) &&
                tickets.equals(that.tickets) &&
                routes.equals(that.routes);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (id.hashCode() + from.hashCode() + to.hashCode() +
                train.hashCode() + departure.hashCode() + arrival.hashCode() +
                ticketsLeft + price + cancelled.hashCode() + delayed.hashCode() +
                tickets.size() + routes.size());
    }
}
