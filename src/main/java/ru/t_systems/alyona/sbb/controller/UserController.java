package ru.t_systems.alyona.sbb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.t_systems.alyona.sbb.dto.ChangeUserDataDTO;
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
        model.addAttribute("changeUserDataDTO", new ChangeUserDataDTO());
        return "employeeAccount";
    }

    @GetMapping(value = "/customer_account")
    public String customerAccount(Model model) {
        //TODO: get passenger
        model.addAttribute("passenger", passengerService.getAllPassengers().get(0));
        model.addAttribute("changeUserDataDTO", new ChangeUserDataDTO());
        return "customerAccount";
    }

    @GetMapping(value = "/crud")
    public String crud(Model model) {
        model.addAttribute("trains", userService.getAllTrainsForCRUD());
        model.addAttribute("stations", userService.getAllStationsForCRUD());
        return "crud";
    }

    @GetMapping(value = "/sign_in")
    public String loginPage(Model model) {
        model.addAttribute("registrationFormDTO", new RegistrationFormDTO());
        return "login";
    }

    @PostMapping(value = "/registration_successful")
    public String registration(@ModelAttribute RegistrationFormDTO registrationFormDTO, Model model) {
        userService.registerUser(registrationFormDTO);
        return "registration_successful";
    }

    @PostMapping(value = "/employee_account")
    public String changeEmployeeLogin(@ModelAttribute ChangeUserDataDTO changeUserDataDTO, Model model) {
        userService.updateEmployeeData(changeUserDataDTO); //TODO change session data
        return "employeeAccount";
    }

    @PostMapping(value = "/customer_account")
    public String changeCustomerData(@ModelAttribute ChangeUserDataDTO changeUserDataDTO, Model model) {
        passengerService.updatePassengerData(changeUserDataDTO); //TODO change session data
        return "customerAccount";
    }
}
