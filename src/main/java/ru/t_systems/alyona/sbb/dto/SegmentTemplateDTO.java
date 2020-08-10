package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

@Data
public class SegmentTemplateDTO {
    private String sourceStation;
    private String destinationStation;
    private int travelDurationMinutes;
    private int stopDurationMinutes;
    private int priceFranks;
}
