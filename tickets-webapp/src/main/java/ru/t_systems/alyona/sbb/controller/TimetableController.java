package ru.t_systems.alyona.sbb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.t_systems.alyona.sbb.service.TimetableService;
import ru.t_systems.alyona.sbb.timetable.TimetableDTO;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @GetMapping("/stations/{stationName}/timetable")
    public TimetableDTO getTimetable(
            @PathVariable("stationName") String stationName,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return timetableService.getTimetableByStationNameAndDate(stationName, date);
    }
}
