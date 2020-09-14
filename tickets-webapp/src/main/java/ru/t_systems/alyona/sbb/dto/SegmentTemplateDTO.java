package ru.t_systems.alyona.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SegmentTemplateDTO {
    private String sourceStation;
    private String destinationStation;
    private int travelDurationMinutes;
    private int stopDurationMinutes;
    private int priceFranks;
    private int indexWithinTrainRoute;
}
