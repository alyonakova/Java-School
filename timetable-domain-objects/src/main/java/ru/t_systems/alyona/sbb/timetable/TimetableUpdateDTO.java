package ru.t_systems.alyona.sbb.timetable;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@Builder
public class TimetableUpdateDTO implements Serializable {

    private String newStatus;
    private String trainNumber;
    private ZonedDateTime departureDate;
}
