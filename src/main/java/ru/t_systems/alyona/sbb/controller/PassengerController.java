package ru.t_systems.alyona.sbb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.t_systems.alyona.sbb.service.PassengerService;

@Controller
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping(value = "/passengers")
    public String showPassengers(Model model) {
        model.addAttribute("allPassengers", passengerService.getPassengersWithTrains());
        return "showPassengers";
    }
}
