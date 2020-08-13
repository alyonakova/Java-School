package ru.t_systems.alyona.sbb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDTO {

    private String text;
    private Severity severity;

    public enum Severity {
        INFORMATIONAL,
        WARNING,
        ERROR,
        TECHNICAL_ERROR,
    }
}
