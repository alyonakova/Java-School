package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t_systems.alyona.sbb.converter.StationConverter;
import ru.t_systems.alyona.sbb.converter.UserConverter;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.repository.StationRepository;
import ru.t_systems.alyona.sbb.repository.UserRepository;
import ru.t_systems.alyona.sbb.service.PassengerService;
import ru.t_systems.alyona.sbb.service.TrainService;
import ru.t_systems.alyona.sbb.service.UserService;

import java.math.BigInteger;
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
    private final PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public void registerUser(RegistrationFormDTO registrationForm) {

        //create passenger
        PassengerDTO passenger = passengerService.createPassenger(registrationForm.getName(),
                registrationForm.getSurname(), registrationForm.getBirthday());

        //create user
        createUser(registrationForm.getLogin(), true, passenger, registrationForm.getUserPassword());
    }

    private void createUser(String login, Boolean isPassenger, PassengerDTO passenger, String password) {
        try {
            final UserDTO userDTO = new UserDTO(null, login, isPassenger, passenger, passwordEncoder.encode(password));
            userRepository.create(userConverter.userToEntity(userDTO));
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
    public UserDTO getUserById(BigInteger id) {
        UserDTO user = null;
        try {
            user = userConverter.userToDTO(userRepository.getById(id));
        } catch (Exception e) {
            LOGGER.error("Failed to get user by ID", e);
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

    @Transactional
    public void updateEmployeeData(ChangeUserDataDTO changeUserDataDTO) {
        if (changeUserDataDTO.getNewLogin() != null) {
            changeLogin(
                    changeUserDataDTO.getNewLogin(),
                    getUserById(changeUserDataDTO.getId())
            );
        } else if (changeUserDataDTO.getNewPassword() != null) {
            changePassword(
                    changeUserDataDTO.getNewPassword(),
                    getUserById(changeUserDataDTO.getId())
            );
        }
    }

    private void changeLogin(String login, UserDTO user) {
        try {
            userRepository.updateLogin(login, userConverter.userToEntity(user));
        } catch (Exception e) {
            LOGGER.error("Failed to update user login", e);
        }
    }

    private void changePassword(String password, UserDTO user) {
        try {
            userRepository.updatePassword(passwordEncoder.encode(password), userConverter.userToEntity(user));
        } catch (Exception e) {
            LOGGER.error("Failed to update user password", e);
        }
    }
}
