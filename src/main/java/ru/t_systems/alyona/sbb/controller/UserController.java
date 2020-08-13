package ru.t_systems.alyona.sbb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.service.PassengerService;
import ru.t_systems.alyona.sbb.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final PassengerService passengerService;
    private final UserService userService;

    @GetMapping(value = "/employee_account")
    public String employeeAccount(Model model, @AuthenticationPrincipal UserDetailsDTO authorizedUserDetails) {
        if (authorizedUserDetails != null) {
            UserDTO user = userService.getUserById(authorizedUserDetails.getId());
            model.addAttribute("userDetails", authorizedUserDetails);
            model.addAttribute("user", user);
            model.addAttribute("changeUserDataDTO", new ChangeUserDataDTO());
            return "employeeAccount";
        } else {
            throw new IllegalStateException("Not authorized to view this page");
        }
    }

    @GetMapping(value = "/customer_account")
    public String customerAccount(Model model, @AuthenticationPrincipal UserDetailsDTO authorizedUserDetails) {
        if (authorizedUserDetails != null) {
            UserDTO user = userService.getUserById(authorizedUserDetails.getId());
            model.addAttribute("userDetails", authorizedUserDetails);
            model.addAttribute("user", user);
            model.addAttribute("changeUserDataDTO", new ChangeUserDataDTO());
            return "customerAccount";
        } else {
            throw new IllegalStateException("Not authorized to view this page"); // TODO: Display error page
        }
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
        UserRegistrationResultDTO result = userService.registerUser(registrationFormDTO);
        if (result.isSuccessful()) {
            model.addAttribute("registrationFormDTO", new RegistrationFormDTO());
            model.addAttribute("messages", result.getMessages());
            return "login";
        }else {
            model.addAttribute("registrationFormDTO", registrationFormDTO);
            model.addAttribute("messages", result.getMessages());
            return "login";
        }
    }

    @PostMapping(value = "/employee_account")
    public String changeEmployeeLogin(@ModelAttribute ChangeUserDataDTO changeUserDataDTO, Model model) {
        userService.updateEmployeeData(changeUserDataDTO);
        return "redirect:/employee_account";
    }

    @PostMapping(value = "/customer_account")
    public String changeCustomerData(@ModelAttribute ChangeUserDataDTO changeUserDataDTO, Model model) {
        passengerService.updatePassengerData(changeUserDataDTO);
        return "redirect:/customer_account";
    }
}
