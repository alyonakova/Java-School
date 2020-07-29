package ru.t_systems.alyona.sbb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.t_systems.alyona.sbb.dto.RegistrationFormDTO;
import ru.t_systems.alyona.sbb.service.PassengerService;
import ru.t_systems.alyona.sbb.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final PassengerService passengerService;
    private final UserService userService;

    @GetMapping(value = "/employee_account")
    public String employeeAccount(Model model) {
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
        model.addAttribute("trains", userService.getAllTrainsForCRUD());
        return "crud";
    }

    @GetMapping(value = "/sign_in")
    public String loginPage(Model model) {
        model.addAttribute("registrationFormDTO", new RegistrationFormDTO());
        return "login";
    }

    @PostMapping(value = "/registration_successful")
    public String registration(@ModelAttribute RegistrationFormDTO registrationFormDTO, Model model) {
        userService.createPassengerUser(registrationFormDTO);
        return "registration_successful";
    }
}
