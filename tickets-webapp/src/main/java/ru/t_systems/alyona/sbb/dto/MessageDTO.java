package ru.t_systems.alyona.sbb.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MessageDTO implements Serializable {

    private String text;
    private Severity severity;

    public enum Severity {
        INFORMATIONAL,
        WARNING,
        ERROR,
        TECHNICAL_ERROR,
    }

    public static MessageDTO error(String text) {
        return MessageDTO.builder()
                .severity(MessageDTO.Severity.ERROR)
                .text(text)
                .build();
    }
}
