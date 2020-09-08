package ru.t_systems.alyona.sbb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.service.PassengerService;
import ru.t_systems.alyona.sbb.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final PassengerService passengerService;
    private final UserService userService;

    @GetMapping(value = "/employee_account")
    public String signInAsEmployee(Model model, @AuthenticationPrincipal UserDetailsDTO authorizedUserDetails) {
        if (authorizedUserDetails != null) {
            UserDTO user = userService.getUserById(authorizedUserDetails.getId());
            model.addAttribute("userDetails", authorizedUserDetails);
            model.addAttribute("user", user);
            model.addAttribute("changeUserDataDTO", new ChangeUserDataDTO());
            return "employeeAccount";
        } else {
            return "access_denied";
        }
    }

    @GetMapping(value = "/customer_account")
    public String signInAsCustomer(Model model, @AuthenticationPrincipal UserDetailsDTO authorizedUserDetails) {
        if (authorizedUserDetails != null) {
            UserDTO user = userService.getUserById(authorizedUserDetails.getId());
            model.addAttribute("userDetails", authorizedUserDetails);
            model.addAttribute("user", user);
            model.addAttribute("tickets", userService.getUserTickets(user));
            model.addAttribute("changeUserDataDTO", new ChangeUserDataDTO());
            return "customerAccount";
        } else {
            return "access_denied";
        }
    }

    @GetMapping(value = "/crud")
    public String showCrudPage() {
        return "crud";
    }

    @GetMapping(value = "/sign_in")
    public String loginPage(Model model) {
        model.addAttribute("registrationFormDTO", new RegistrationFormDTO());
        return "login";
    }

    @PostMapping(value = "/registration_status")
    public String registerUser(@Valid @ModelAttribute RegistrationFormDTO registrationFormDTO,
                               BindingResult validationResult, Model model) {
        model.addAttribute("validationErrors", validationResult.getAllErrors());
        if (validationResult.hasErrors()) {
            model.addAttribute("registrationFormDTO", registrationFormDTO);
            return "login";
        }
        UserRegistrationResultDTO result = userService.registerUser(registrationFormDTO);
        if (result.isSuccessful()) {
            model.addAttribute("registrationFormDTO", new RegistrationFormDTO());
        } else model.addAttribute("registrationFormDTO", registrationFormDTO);

        model.addAttribute("messages", result.getMessages());
        return "login";
    }

    @PostMapping(value = "/employee_account")
    public String changeEmployeeData(@Valid @ModelAttribute ChangeUserDataDTO changeUserDataDTO,
                                     BindingResult validationResult, Model model,
                                     @AuthenticationPrincipal UserDetailsDTO authorizedUserDetails) {
        model.addAttribute("validationErrors", validationResult.getAllErrors());
        model.addAttribute("userDetails", authorizedUserDetails);
        model.addAttribute("user", userService.getUserById(authorizedUserDetails.getId()));
        model.addAttribute("changeUserDataDTO", new ChangeUserDataDTO());
        if (validationResult.hasErrors()) {
            return "employeeAccount";
        }
        OperationResultDTO result = userService.updateEmployeeData(changeUserDataDTO);
        model.addAttribute("messages", result.getMessages());
        return "employeeAccount";
    }

    @PostMapping(value = "/customer_account")
    public String changeCustomerData(@Valid @ModelAttribute ChangeUserDataDTO changeUserDataDTO,
                                     BindingResult validationResult, Model model,
                                     @AuthenticationPrincipal UserDetailsDTO authorizedUserDetails) {
        model.addAttribute("validationErrors", validationResult.getAllErrors());
        model.addAttribute("userDetails", authorizedUserDetails);
        UserDTO user = userService.getUserById(authorizedUserDetails.getId());
        model.addAttribute("user", user);
        model.addAttribute("tickets", userService.getUserTickets(user));
        model.addAttribute("changeUserDataDTO", new ChangeUserDataDTO());
        if (validationResult.hasErrors()) {
            return "customerAccount";
        }
        OperationResultDTO result = passengerService.updatePassengerData(changeUserDataDTO);
        model.addAttribute("messages", result.getMessages());
        return "customerAccount";
    }

    @GetMapping("/fail-sign-in")
    public String failSigningIn(Model model) {
        model.addAttribute("messageWrongCredentials", userService.displayUnsuccessfulSignIn());
        model.addAttribute("registrationFormDTO", new RegistrationFormDTO());
        return "login";
    }
}
