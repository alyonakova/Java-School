package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
public class BuyTicketFormDTO {

    private UUID connectionId;

    @NotNull(message = "At least one passenger must be registered")
    @Size(min = 1, max = 15, message = "Passengers count must be not less than 1 and not more than 15")
    @Valid
    private List<PassengerDTO> passengers;
}
