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
public class UserController {

    private final PassengerService passengerService;

    @GetMapping(value = "/employee_account")
    public String employeeAccount(Model model) {
        //TODO: get login
        model.addAttribute("login", "employee123");
        return "employeeAccount";
    }

    @GetMapping(value = "/customer_account")
    public String customerAccount(Model model) {
        //TODO: get passenger
        model.addAttribute("passenger", passengerService.getAllPassengers().get(0));
        return "customerAccount";
    }

    @GetMapping(value = "/crud")
    public String crud(Model model) {
        return "crud";
    }

    @GetMapping(value = "/sign_in")
    public String loginPage(Model model) {
        return "login";
    }
}
