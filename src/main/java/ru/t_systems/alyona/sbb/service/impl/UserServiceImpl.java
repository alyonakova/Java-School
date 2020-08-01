package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
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
        userRepository.create(
                userConverter.userToEntity(new UserDTO(null, login, isPassenger, passenger, password))
        );
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        return userConverter.userToDTO(userRepository.getByLogin(login));
    }

    @Override
    public List<TrainDTO> getAllTrainsForCRUD() {
        return trainService.getAllTrains();
    }

    @Override
    public List<StationDTO> getAllStationsForCRUD() {
        return stationConverter.stationListToDTOList(stationRepository.getAll());
    }

    @Override
    public void updateUserLogin(String login, UserDTO user) {
        userRepository.updateLogin(login, userConverter.userToEntity(user));
    }
}
