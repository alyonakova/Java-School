package ru.t_systems.alyona.sbb.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Data
@RequiredArgsConstructor
public class TicketController {

    @GetMapping(value = "/ticket")
    public String crud(Model model) {
        return "buyTickets";
    }
}
