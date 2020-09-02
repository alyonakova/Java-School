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

    public static ConnectionSearchResultDTO error(String errorMessageText) {
        return builder()
                .successful(false)
                .messages(List.of(
                        MessageDTO.builder()
                                .text(errorMessageText)
                                .severity(MessageDTO.Severity.ERROR)
                                .build()))
                .build();
    }
}
