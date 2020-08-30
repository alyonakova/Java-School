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
import java.util.List;

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
        TrainDTO train = trainService.getById(trainNumber);
        List<TrainDepartureDTO> trainDepartures = trainService.getDeparturesByTrain(train);
        fillTrainItemPage(model, train, trainDepartures);
        return "trainItem";
    }

    @PostMapping(value = "/trains/{id}/cancel")
    public String cancelTrain(@PathVariable("id") String trainNumber, Model model) {
        TrainDTO train = trainService.getById(trainNumber);
        List<TrainDepartureDTO> trainDepartures = trainService.getDeparturesByTrain(train);
        OperationResultDTO result = trainService.cancelTrain(train);
        model.addAttribute("messages", result.getMessages());
        fillTrainItemPage(model, train, trainDepartures);
        return "trainItem";
    }

    @PostMapping(value = "/trains/{id}/restore")
    public String restoreTrain(@PathVariable("id") String trainNumber, Model model) {
        TrainDTO train = trainService.getById(trainNumber);
        List<TrainDepartureDTO> trainDepartures = trainService.getDeparturesByTrain(train);
        OperationResultDTO result = trainService.restoreTrain(train);
        model.addAttribute("messages", result.getMessages());
        fillTrainItemPage(model, train, trainDepartures);
        return "trainItem";
    }

    public void fillTrainItemPage(Model model, TrainDTO train,
                                  List<TrainDepartureDTO> trainDepartures) {
        model.addAttribute("train", train);
        model.addAttribute("trainDepartures", trainDepartures);
        model.addAttribute("segments", trainService.getSegmentsByTrainNumber(train));
        model.addAttribute("cancelled", trainService.isTrainCancelled(trainDepartures));
        model.addAttribute("delayForm", new DelayFormDTO());
    }

    @PostMapping(value = "/trains/{id}/delay")
    public String delayTrain(@PathVariable("id") String trainNumber, @ModelAttribute DelayFormDTO delayForm, Model model) {
        TrainDTO train = trainService.getById(trainNumber);
        List<TrainDepartureDTO> trainDepartures = trainService.getDeparturesByTrain(train);
        OperationResultDTO result = trainService.delayTrain(train, delayForm.getDelayInMinutes());
        model.addAttribute("messages", result.getMessages());
        fillTrainItemPage(model, train, trainDepartures);
        return "trainItem";
    }

    @GetMapping(value = "/trains/{id}/departures/{departureTime}")
    public String showTrainDeparture(@PathVariable("id") String trainNumber,
                                     @PathVariable("departureTime") String departureTime, Model model) {
        TrainDepartureDTO trainDeparture = trainService.getTrainDeparture(trainNumber, departureTime);
        model.addAttribute("trainDeparture", trainDeparture);
        model.addAttribute("delayForm", new DelayFormDTO());
        return "trainDeparture";
    }

    @PostMapping(value = "/trains/{id}/departures/{departureTime}/cancel")
    public String cancelDeparture(@PathVariable("id") String trainNumber,
                                  @PathVariable("departureTime") String departureTime, Model model) {
        TrainDepartureDTO trainDeparture = trainService.getTrainDeparture(trainNumber, departureTime);
        OperationResultDTO result = trainService.cancelTrainDeparture(trainDeparture);
        model.addAttribute("trainDeparture", trainDeparture);
        model.addAttribute("messages", result.getMessages());
        model.addAttribute("delayForm", new DelayFormDTO());
        return "trainDeparture";
    }

    @PostMapping(value = "/trains/{id}/departures/{departureTime}/restore")
    public String restoreDeparture(@PathVariable("id") String trainNumber,
                                  @PathVariable("departureTime") String departureTime, Model model) {
        TrainDepartureDTO trainDeparture = trainService.getTrainDeparture(trainNumber, departureTime);
        OperationResultDTO result = trainService.restoreTrainDeparture(trainDeparture);
        model.addAttribute("trainDeparture", trainDeparture);
        model.addAttribute("messages", result.getMessages());
        model.addAttribute("delayForm", new DelayFormDTO());
        return "trainDeparture";
    }

    @PostMapping(value = "/trains/{id}/departures/{departureTime}/delay")
    public String delayDeparture(@PathVariable("id") String trainNumber,
                                 @PathVariable("departureTime") String departureTime,
                                 @ModelAttribute DelayFormDTO delayForm, Model model) {
        TrainDepartureDTO trainDeparture = trainService.getTrainDeparture(trainNumber, departureTime);
        OperationResultDTO result = trainService.delayTrainDeparture(trainDeparture, delayForm.getDelayInMinutes());
        model.addAttribute("trainDeparture", trainDeparture);
        model.addAttribute("messages", result.getMessages());
        model.addAttribute("delayForm", new DelayFormDTO());
        return "trainDeparture";
    }

}
