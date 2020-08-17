package ru.t_systems.alyona.sbb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.t_systems.alyona.sbb.dto.OperationResultDTO;
import ru.t_systems.alyona.sbb.dto.StationDTO;
import ru.t_systems.alyona.sbb.service.StationService;

@Controller
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @PostMapping(value = "/api/stations", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addStation(@RequestBody StationDTO request, Model model) {
        OperationResultDTO result = stationService.createStation(request);
        model.addAttribute("stationCreationMessages", result.getMessages());
        return "crud";
    }
}
