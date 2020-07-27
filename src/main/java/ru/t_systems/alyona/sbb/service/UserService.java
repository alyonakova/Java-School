package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.RegistrationFormDTO;
import ru.t_systems.alyona.sbb.dto.UserDTO;

@Service
public interface UserService {

    void createPassengerUser(RegistrationFormDTO registrationForm);
    UserDTO getUserByLogin(String login);
}
