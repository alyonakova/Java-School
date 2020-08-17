package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BuyTicketFormDTO {

    private UUID connectionId;
    private List<PassengerDTO> passengers;
}
