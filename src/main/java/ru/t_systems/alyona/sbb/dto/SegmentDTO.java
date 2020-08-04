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
    private int ticketsLeft;
    private int price;
    private Boolean cancelled;
    private Boolean delayed;

}
