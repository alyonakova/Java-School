package ru.t_systems.alyona.sbb.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Comparator;

@Data
@Builder
public class SegmentTemplateDTO {
    private String sourceStation;
    private String destinationStation;
    private int travelDurationMinutes;
    private int stopDurationMinutes;
    private int priceFranks;
    private int indexWithinTrainRoute;

    public static final Comparator<SegmentTemplateDTO> COMPARE_BY_ROUTE_INDEX = new Comparator<SegmentTemplateDTO>() {
        @Override
        public int compare(SegmentTemplateDTO o1, SegmentTemplateDTO o2) {
            return o1.getIndexWithinTrainRoute() - o2.getIndexWithinTrainRoute();
        }
    };
}
