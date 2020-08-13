package ru.t_systems.alyona.sbb.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserRegistrationResultDTO {

    private boolean successful;
    private List<MessageDTO> messages;
}
