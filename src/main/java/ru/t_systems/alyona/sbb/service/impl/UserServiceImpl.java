package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.converter.StationConverter;
import ru.t_systems.alyona.sbb.converter.UserConverter;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.repository.StationRepository;
import ru.t_systems.alyona.sbb.repository.UserRepository;
import ru.t_systems.alyona.sbb.service.PassengerService;
import ru.t_systems.alyona.sbb.service.TrainService;
import ru.t_systems.alyona.sbb.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final PassengerService passengerService;
    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final TrainService trainService;
    private final StationConverter stationConverter;
    private final StationRepository stationRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public void createPassengerUser(RegistrationFormDTO registrationForm) {

        //create passenger
        PassengerDTO passenger = passengerService.createPassenger(registrationForm.getName(),
                registrationForm.getSurname(), registrationForm.getBirthday());

        //create user
        createUser(registrationForm.getLogin(), true, passenger, registrationForm.getUserPassword());
    }

    private void createUser(String login, Boolean isPassenger, PassengerDTO passenger, String password) {
        try {
            userRepository.create(
                    userConverter.userToEntity(new UserDTO(null, login, isPassenger, passenger, password))
            );
        } catch (Exception e) {
            LOGGER.error("Failed to create a new user", e);
        }
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        UserDTO user = null;
        try {
            user = userConverter.userToDTO(userRepository.getByLogin(login));
        } catch (Exception e) {
            LOGGER.error("Failed to get user by login", e);
        }
        return user;
    }

    @Override
    public List<TrainDTO> getAllTrainsForCRUD() {
        List<TrainDTO> result = null;
        try {
            result = trainService.getAllTrains();
        } catch (Exception e) {
            LOGGER.error("Failed to get all existing trains", e);
        }
        return result;
    }

    @Override
    public List<StationDTO> getAllStationsForCRUD() {
        List<StationDTO> result = null;
        try {
            result = stationConverter.stationListToDTOList(stationRepository.getAll());
        } catch (Exception e) {
            LOGGER.error("Failed to get all existing stations", e);
        }
        return result;
    }

    public void updateEmployeeData(ChangeUserDataDTO changeUserDataDTO) {
        //TODO
        if (changeUserDataDTO.getNewLogin() != null) {
            changeLogin(
                    changeUserDataDTO.getNewLogin(),
                    userConverter.userToDTO(userRepository.getByLogin(changeUserDataDTO.getLogin()))
            );
        } else if (changeUserDataDTO.getNewPassword() != null) {
            //change password
        }
    }

    private void changeLogin(String login, UserDTO user) {
        try {
            userRepository.updateLogin(login, userConverter.userToEntity(user));
        } catch (Exception e) {
            LOGGER.error("Failed to update user login", e);
        }
    }

    private void changePassword(){}
}
