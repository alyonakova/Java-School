package ru.t_systems.alyona.sbb.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.t_systems.alyona.sbb.service.SegmentService;
import ru.t_systems.alyona.sbb.service.StationService;

import java.time.Instant;

@Controller
@Data
@RequiredArgsConstructor
public class TrainController {

    private final StationService stationService;
    private final SegmentService segmentService;

    @GetMapping(value = "/trains")
    public String testDB(Model model) {

        //TODO: get these values from the form
        String firstStationName = "Green Hills";
        String secondStationName = "Chemical Plant";
        Instant firstDate = Instant.parse("2020-10-02T10:10:00.00Z");
        Instant secondDate = Instant.parse("2020-10-29T10:10:00.00Z");

        model.addAttribute("segmentGroups", segmentService.getSegmentGroupsByStationsAndDates(
                firstStationName, secondStationName, firstDate, secondDate));
        return "trains";
    }

}
