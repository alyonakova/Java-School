package ru.t_systems.alyona.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * A specific way to travel from one station to another, possibly on several trains.
 * <p>
 * A connection is a sequence of rides on different trains (so-called chains) with known departure and arrival times.
 *
 * @see ChainDTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionDTO {

    private UUID id;

    private int priceFranks;
    private List<ChainDTO> chains;

    private ZonedDateTime departureTime;
    private ZonedDateTime arrivalTime;

    private int availableTicketsCount;

    public StationDTO getDepartureStation() {
        return chains.get(0).getDepartureStation();
    }

    public StationDTO getArrivalStation() {
        return chains.get(chains.size() - 1).getArrivalStation();
    }

    public Duration getTotalDuration() {
        return Duration.between(departureTime, arrivalTime);
    }

    public int getTransfersCount() {
        return getChains().size() - 1;
    }
}
