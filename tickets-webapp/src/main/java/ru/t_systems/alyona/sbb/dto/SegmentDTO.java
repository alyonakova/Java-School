package ru.t_systems.alyona.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.math.BigInteger;
import java.time.Instant;

/**
 * A specific way to go from one station to another without stopping.
 * Departure and arrival times are known, as well as the train number and the price.
 *
 * @see ChainDTO
 * @see ConnectionDTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SegmentDTO {

    private BigInteger id;

    // Origin of this segment
    private BigInteger segmentTemplateId;
    private Instant trainDepartureTime;

    // Details
    private StationDTO from;
    private StationDTO to;
    private TrainDTO train;
    private Instant departureTime;
    private Instant arrivalTime;

    @Min(value = 0, message = "Tickets count cannot be negative")
    private int ticketsLeft;

    @Min(value = 0, message = "Price cannot be negative")
    private int price;

    private Boolean cancelled;
    private int delayedMinutes;

}
