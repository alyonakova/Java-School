package ru.t_systems.alyona.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.t_systems.alyona.sbb.dto.ConnectionSearchQueryDTO;

import java.time.LocalDateTime;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String goToHomepage(Model model) {
        ConnectionSearchQueryDTO newQuery = new ConnectionSearchQueryDTO();
        newQuery.setDepartureTime(LocalDateTime.now());
        newQuery.setArrivalTime(LocalDateTime.now().plusDays(2L));
        model.addAttribute("outboundQuery", newQuery);
        return "index";
    }

}
