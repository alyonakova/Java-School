package ru.t_systems.alyona.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OperationResultDTO {

    private boolean successful;
    private List<MessageDTO> messages;

    public OperationResultDTO() {
        this.successful = false;
        this.messages = new ArrayList<>();
    }

    public static OperationResultDTO successful(String messageText) {
        return builder()
                .successful(true)
                .messages(List.of(
                        MessageDTO.builder()
                                .severity(MessageDTO.Severity.INFORMATIONAL)
                                .text(messageText)
                                .build()
                ))
                .build();
    }

    public static OperationResultDTO error(String errorMessageText) {
        return builder()
                .successful(false)
                .messages(List.of(
                        MessageDTO.builder()
                                .text(errorMessageText)
                                .severity(MessageDTO.Severity.ERROR)
                                .build()))
                .build();
    }

    public static OperationResultDTO technicalError(String errorMessageText) {
        return builder()
                .successful(false)
                .messages(List.of(
                        MessageDTO.builder()
                                .text(errorMessageText)
                                .severity(MessageDTO.Severity.TECHNICAL_ERROR)
                                .build()))
                .build();
    }
}
