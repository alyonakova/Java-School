package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import javax.validation.constraints.Min;
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

    @Min(value = 0, message = "Tickets count cannot be negative")
    private int ticketsLeft;

    @Min(value = 0, message = "Price cannot be negative")
    private int price;

    private Boolean cancelled;
    private Boolean delayed;

}
