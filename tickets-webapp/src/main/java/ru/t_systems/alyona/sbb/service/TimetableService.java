package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.timetable.TimetableDTO;

import java.time.LocalDate;

@Service
public interface TimetableService {

    TimetableDTO getTimetableByStationNameAndDate(String stationName, LocalDate date);

}
