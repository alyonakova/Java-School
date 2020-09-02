package ru.t_systems.alyona.sbb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.service.ConnectionSearchService;
import ru.t_systems.alyona.sbb.service.TrainService;

import javax.inject.Provider;
import javax.validation.Valid;
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
        List<SegmentTemplateDTO> segmentTemplates = trainService.getSegmentsByTrainNumber(train);
        boolean isTrainCancelled = trainService.isTrainCancelled(trainDepartures);

        model.addAttribute("train", train);
        model.addAttribute("trainDepartures", trainDepartures);
        model.addAttribute("segments", segmentTemplates);
        model.addAttribute("cancelled", isTrainCancelled);
        model.addAttribute("delayForm", new DelayFormDTO());
        return "trainItem";
    }

    @PostMapping(value = "/trains/{id}/cancel")
    public String cancelTrain(@PathVariable("id") String trainNumber,
                              RedirectAttributes redirectAttributes) {

        TrainDTO train = trainService.getById(trainNumber);
        OperationResultDTO result = trainService.cancelTrain(train);

        redirectAttributes.addFlashAttribute("messages", result.getMessages());
        return "redirect:/trains/{id}";
    }

    @PostMapping(value = "/trains/{id}/restore")
    public String restoreTrain(@PathVariable("id") String trainNumber,
                               RedirectAttributes redirectAttributes) {

        TrainDTO train = trainService.getById(trainNumber);
        OperationResultDTO result = trainService.restoreTrain(train);

        redirectAttributes.addFlashAttribute("messages", result.getMessages());
        return "redirect:/trains/{id}";
    }

    @PostMapping(value = "/trains/{id}/delay")
    public String delayTrain(@PathVariable("id") String trainNumber,
                             @Valid @ModelAttribute DelayFormDTO delayForm,
                             BindingResult validationResult,
                             RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("validationErrors", validationResult.getAllErrors());

        if (validationResult.hasErrors()) {
            return "redirect:/trains/{id}";
        }

        TrainDTO train = trainService.getById(trainNumber);
        OperationResultDTO result = trainService.delayTrain(train, Integer.parseInt(delayForm.getDelayInMinutes()));

        redirectAttributes.addFlashAttribute("messages", result.getMessages());
        return "redirect:/trains/{id}";
    }

    @GetMapping(value = "/trains/{id}/departures/{departureTime}")
    public String showTrainDeparture(@PathVariable("id") String trainNumber,
                                     @PathVariable("departureTime") String departureTime,
                                     Model model) {

        TrainDepartureDTO trainDeparture = trainService.getTrainDeparture(trainNumber, departureTime);

        model.addAttribute("trainDeparture", trainDeparture);
        model.addAttribute("delayForm", new DelayFormDTO());
        return "trainDeparture";
    }

    @PostMapping(value = "/trains/{id}/departures/{departureTime}/cancel")
    public String cancelDeparture(@PathVariable("id") String trainNumber,
                                  @PathVariable("departureTime") String departureTime,
                                  RedirectAttributes redirectAttributes) {

        TrainDepartureDTO trainDeparture = trainService.getTrainDeparture(trainNumber, departureTime);
        OperationResultDTO result = trainService.cancelTrainDeparture(trainDeparture);

        redirectAttributes.addFlashAttribute("messages", result.getMessages());
        return "redirect:/trains/{id}/departures/{departureTime}";
    }

    @PostMapping(value = "/trains/{id}/departures/{departureTime}/restore")
    public String restoreDeparture(@PathVariable("id") String trainNumber,
                                   @PathVariable("departureTime") String departureTime,
                                   RedirectAttributes redirectAttributes) {

        TrainDepartureDTO trainDeparture = trainService.getTrainDeparture(trainNumber, departureTime);
        OperationResultDTO result = trainService.restoreTrainDeparture(trainDeparture);

        redirectAttributes.addFlashAttribute("messages", result.getMessages());
        return "redirect:/trains/{id}/departures/{departureTime}";
    }

    @PostMapping(value = "/trains/{id}/departures/{departureTime}/delay")
    public String delayDeparture(@PathVariable("id") String trainNumber,
                                 @PathVariable("departureTime") String departureTime,
                                 @Valid @ModelAttribute DelayFormDTO delayForm,
                                 BindingResult validationResult,
                                 RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("validationErrors", validationResult.getAllErrors());

        if (validationResult.hasErrors()) {
            return "redirect:/trains/{id}/departures/{departureTime}";
        }

        TrainDepartureDTO trainDeparture = trainService.getTrainDeparture(trainNumber, departureTime);
        OperationResultDTO result = trainService.delayTrainDeparture(trainDeparture, Integer.parseInt(delayForm.getDelayInMinutes()));

        redirectAttributes.addFlashAttribute("messages", result.getMessages());
        return "redirect:/trains/{id}/departures/{departureTime}";
    }

}
