package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.*;

import java.util.List;

@Service
public interface UserService {

    void registerUser(RegistrationFormDTO registrationForm);
    UserDTO getUserByLogin(String login);
    List<TrainDTO> getAllTrainsForCRUD();
    List<StationDTO> getAllStationsForCRUD();
    void updateEmployeeData(ChangeUserDataDTO changeUserDataDTO);
}
