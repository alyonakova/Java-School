package ru.t_systems.alyona.sbb.timetable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimetableDTO implements Serializable {

    private String stationName;
    private LocalDate date;
    private List<TimetableSegmentDTO> segments;
}
