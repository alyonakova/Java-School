package ru.t_systems.alyona.sbb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.t_systems.alyona.sbb.dto.CreateTrainRequestDTO;
import ru.t_systems.alyona.sbb.dto.FindTrainFormDTO;
import ru.t_systems.alyona.sbb.dto.SegmentsGroupDTO;
import ru.t_systems.alyona.sbb.service.SegmentService;
import ru.t_systems.alyona.sbb.service.TrainService;

@Controller
@RequiredArgsConstructor
public class TrainController {

    private final TrainService trainService;
    private final SegmentService segmentService;

    @PostMapping(value = "/")
    public String showTrains(@ModelAttribute FindTrainFormDTO findTrainFormDTO, Model model) {

        model.addAttribute("segmentGroups", segmentService.getSegmentGroupsByStationsAndDates(
                findTrainFormDTO.getFirstStationName(), findTrainFormDTO.getSecondStationName(),
                findTrainFormDTO.getFirstDate(), findTrainFormDTO.getSecondDate()
        ));
        model.addAttribute("segmentsGroupDTO", new SegmentsGroupDTO());
        return "trains";
    }

    @PostMapping(value = "/api/trains", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTrain(@RequestBody CreateTrainRequestDTO request) {
        trainService.createTrain(request);
    }

}
