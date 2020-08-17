package ru.t_systems.alyona.sbb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.service.ConnectionSearchService;
import ru.t_systems.alyona.sbb.service.TrainService;

import javax.inject.Provider;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class TrainController {

    private final TrainService trainService;
    private final ConnectionSearchService connectionSearchService;
    private final Provider<ConnectionCache> connectionCacheProvider;

    @PostMapping(path = "/connections")
    public String findConnections(@ModelAttribute ConnectionSearchQueryDTO connectionSearchQueryDTO,
                                  HttpSession session,
                                  Model model) {

        ConnectionSearchResultDTO result = connectionSearchService.findConnections(connectionSearchQueryDTO);
        if (result.isSuccessful()) {
            model.addAttribute("query", connectionSearchQueryDTO);
            model.addAttribute("discoveredConnections", result.getDiscoveredConnections());
            model.addAttribute("selectedConnection", new ConnectionDTO());

            connectionCacheProvider.get().putAll(result.getDiscoveredConnections());
            return "connectionSearchResults";
        } else {
            model.addAttribute("messageConnectionSearchData", result.getMessages());
            model.addAttribute("outboundQuery", new ConnectionSearchQueryDTO());
            return "index";
        }
    }

    @PostMapping(value = "/api/trains", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTrain(@RequestBody CreateTrainRequestDTO request) {
        trainService.createTrain(request);
    }

}
