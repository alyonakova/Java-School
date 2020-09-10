package ru.t_systems.alyona.sbb.timetable;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
public class TimetableUpdateDTO implements Serializable {

    private String newStatus;
    private String trainNumber;
    private List<ZonedDateTime> trainDepartureDates;
}
