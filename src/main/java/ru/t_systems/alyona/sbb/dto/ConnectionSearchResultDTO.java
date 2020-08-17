package ru.t_systems.alyona.sbb.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ConnectionSearchResultDTO {

    private boolean successful;
    private List<ConnectionDTO> discoveredConnections;
    private List<MessageDTO> messages;
}
