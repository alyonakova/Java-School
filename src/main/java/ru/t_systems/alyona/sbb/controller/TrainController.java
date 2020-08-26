package ru.t_systems.alyona.sbb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.service.ConnectionSearchService;
import ru.t_systems.alyona.sbb.service.TrainService;

import javax.inject.Provider;

@Controller
@RequiredArgsConstructor
public class TrainController {

    private final TrainService trainService;
    private final ConnectionSearchService connectionSearchService;
    private final Provider<ConnectionCache> connectionCacheProvider;

    @PostMapping(path = "/connections")
    public String findConnections(@ModelAttribute ConnectionSearchQueryDTO query, Model model) {

        ConnectionSearchResultDTO result = connectionSearchService.findConnections(query);
        if (result.isSuccessful()) {
            model.addAttribute("query", query);
            model.addAttribute("discoveredConnections", result.getDiscoveredConnections());
            model.addAttribute("selectedConnection", new ConnectionDTO());
            connectionCacheProvider.get().putAll(result.getDiscoveredConnections());
            return "connectionSearchResults";
        } else {
            model.addAttribute("messageConnectionSearchData", result.getMessages());
            model.addAttribute("outboundQuery", query);
            return "index";
        }
    }

    @PostMapping(value = "/api/trains", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OperationResultDTO addTrain(@RequestBody CreateTrainRequestDTO request) {
        return trainService.createTrain(request);
    }

    @GetMapping(value = "/trains")
    public String showAllTrains(Model model) {
        model.addAttribute("trains", trainService.getAllTrains());
        return "trainsList";
    }

    @GetMapping(value = "/trains/{id}")
    public String showTrainItem(@PathVariable("id") String trainNumber, Model model) {
        model.addAttribute("trainDepartures", trainService.getDeparturesByTrain(trainNumber));
        model.addAttribute("segments", trainService.getSegmentsByTrainNumber(trainNumber));
        return "trainItem";
    }

}
