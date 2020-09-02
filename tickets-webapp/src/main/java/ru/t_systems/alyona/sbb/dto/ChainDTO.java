package ru.t_systems.alyona.sbb.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * A specific ride on one train.
 * <p>
 * A chain is a sequence of adjacent segments of the same train with known departure and arrival times.
 *
 * @see SegmentDTO
 */
@Data
@Builder
public class ChainDTO {

    private String trainNumber;
    private List<SegmentDTO> segments;

    private StationDTO departureStation;
    private ZonedDateTime departureTime;

    private StationDTO arrivalStation;
    private ZonedDateTime arrivalTime;

    public String getViaStationsText() {
        return segments.stream()
                .skip(1)
                .map(SegmentDTO::getFrom)
                .map(StationDTO::getName)
                .collect(joining(", "));
    }

    public Duration getDuration() {
        return Duration.between(departureTime, arrivalTime);
    }
}
