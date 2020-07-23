package ru.t_systems.alyona.sbb.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.t_systems.alyona.sbb.service.PassengerService;

@Controller
@Data
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping(value = "/passengers")
    public String home(Model model) {
        model.addAttribute("allPassengers", passengerService.getAllPassengers());
        return "showPassengers";
    }
}
