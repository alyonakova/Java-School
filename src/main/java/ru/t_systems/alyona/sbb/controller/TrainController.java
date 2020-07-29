package ru.t_systems.alyona.sbb.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.t_systems.alyona.sbb.dto.FindTrainFormDTO;
import ru.t_systems.alyona.sbb.dto.SegmentsGroupDTO;
import ru.t_systems.alyona.sbb.service.SegmentService;

@Controller
@Data
@RequiredArgsConstructor
public class TrainController {

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

}
