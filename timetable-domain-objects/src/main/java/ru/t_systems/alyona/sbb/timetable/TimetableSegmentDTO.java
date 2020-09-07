package ru.t_systems.alyona.sbb.timetable;

import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimetableSegmentDTO implements Serializable {

    private String trainNumber;

    private ZonedDateTime departureDate;

    private String departureStation;

    private ZonedDateTime arrivalDate;

    private String arrivalStation;

    private String status;
}
