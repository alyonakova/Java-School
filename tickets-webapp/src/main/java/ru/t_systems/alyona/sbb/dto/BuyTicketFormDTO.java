package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class BuyTicketFormDTO {

    private UUID connectionId;

    @NotNull(message = "At least one passenger must be registered")
    private List<PassengerDTO> passengers;
}
