package ru.t_systems.alyona.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketSegmentDTO {

    BigInteger ticketId;
    Integer indexInTicket;
    BigInteger segmentTemplateId;
    Instant trainDepartureTime;
    Integer trainCapacity;
}
