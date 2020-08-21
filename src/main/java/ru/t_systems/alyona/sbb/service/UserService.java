package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.*;

import java.math.BigInteger;
import java.util.List;

@Service
public interface UserService {

    UserRegistrationResultDTO registerUser(RegistrationFormDTO registrationForm);

    UserDTO getUserByLogin(String login);

    List<TrainDTO> getAllTrainsForCRUD();

    List<StationDTO> getAllStationsForCRUD();

    OperationResultDTO updateEmployeeData(ChangeUserDataDTO changeUserDataDTO);

    UserDTO getUserById(BigInteger id);

    MessageDTO displayUnsuccessfulSignIn();
}
