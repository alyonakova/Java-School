package ru.t_systems.alyona.sbb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.t_systems.alyona.sbb.dto.BuyTicketFormDTO;
import ru.t_systems.alyona.sbb.dto.ConnectionDTO;
import ru.t_systems.alyona.sbb.dto.MessageDTO;
import ru.t_systems.alyona.sbb.dto.OperationResultDTO;
import ru.t_systems.alyona.sbb.service.TicketService;

import javax.inject.Provider;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class TicketController {

    private final Provider<ConnectionCache> connectionCacheProvider;
    private final TicketService ticketService;

    @GetMapping(path = "/connections/{id}")
    public String showPassengersForm(@PathVariable("id") UUID connectionId,
                                     Model model) {
        ConnectionDTO connection = connectionCacheProvider.get().findById(connectionId);
        if (connection == null) {
            model.addAttribute("messages", MessageDTO.builder()
                    .severity(MessageDTO.Severity.ERROR)
                    .text("No such route found, please repeat your search.")
                    .build());
            return "index";
        }
        model.addAttribute("connection", connection);
        return "buyTickets";
    }

    @PostMapping(path = "/buyTickets")
    public String buyTickets(@RequestBody BuyTicketFormDTO request, Model model) {
        OperationResultDTO result = ticketService.buy(request);
        model.addAttribute("messages", result.getMessages());
        return "buyTickets";
    }
}
