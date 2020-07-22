package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class TicketDTO {

    private BigInteger id;
    private PassengerDTO passenger;
    private List<SegmentDTO> segments;

}
