package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t_systems.alyona.sbb.converter.PassengerConverter;
import ru.t_systems.alyona.sbb.converter.UserConverter;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.entity.PassengerEntity;
import ru.t_systems.alyona.sbb.repository.PassengerRepository;
import ru.t_systems.alyona.sbb.repository.UserRepository;
import ru.t_systems.alyona.sbb.service.PassengerService;
import ru.t_systems.alyona.sbb.service.TicketService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerConverter passengerConverter;
    private final PassengerRepository passengerRepository;
    private final TicketService ticketService;
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(PassengerServiceImpl.class);

    @Override
    public List<PassengerDTO> getAllPassengers() {
        List<PassengerDTO> result = null;
        try {
            result = passengerConverter.toDTOList(passengerRepository.getAll());
        } catch (Exception e) {
            LOGGER.error("Failed to get all passengers", e);
        }
        return result;
    }

    @Override
    public Set<PassengerWithTrainDTO> getPassengersWithTrains() {
        List<TicketDTO> allTickets = ticketService.getAllTickets();
        Set<PassengerWithTrainDTO> passengerTrainSet = new HashSet<>();
        try {
            for (TicketDTO ticket : allTickets) {
                for (SegmentDTO segment : ticket.getSegments()) {
                    PassengerWithTrainDTO passengerWithTrain = new PassengerWithTrainDTO(
                            ticket.getPassenger(), segment.getTrain());
                    passengerTrainSet.add(passengerWithTrain);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to get passengers with their trains", e);
        }
        return passengerTrainSet;
    }

    @Override
    public PassengerDTO createPassenger(String name, String surname, LocalDate birthday) {
        PassengerDTO passenger = null;
        try {
            final PassengerEntity newPassanger = passengerConverter.toEntity(
                    new PassengerDTO(null, name, surname, birthday));
            final PassengerEntity createdPassenger = passengerRepository.create(newPassanger);
            passenger = passengerConverter.toDTO(createdPassenger);
        } catch (Exception e) {
            LOGGER.error("Failed to create new passenger", e);
        }
        return passenger;
    }

    @Override
    @Transactional
    public void updatePassengerData(ChangeUserDataDTO changeUserDataDTO) {
        if (changeUserDataDTO.getNewLogin() != null) {
            changeLogin(
                    changeUserDataDTO.getNewLogin(),
                    userConverter.toDTO(userRepository.getById(changeUserDataDTO.getId()))
            );
        } else if (changeUserDataDTO.getNewPassword() != null) {
            changePassword(
                    changeUserDataDTO.getNewPassword(),
                    userConverter.toDTO(userRepository.getById(changeUserDataDTO.getId()))
            );
        } else if (changeUserDataDTO.getNewName() != null) {
            changeName(
                    changeUserDataDTO.getNewName(),
                    userConverter.toDTO(userRepository.getById(changeUserDataDTO.getId()))
            );
        } else if (changeUserDataDTO.getNewSurname() != null) {
            changeSurname(
                    changeUserDataDTO.getNewSurname(),
                    userConverter.toDTO(userRepository.getById(changeUserDataDTO.getId()))
            );
        } else if (changeUserDataDTO.getNewBirthday() != null) {
            changeBirthday(
                    changeUserDataDTO.getNewBirthday(),
                    userConverter.toDTO(userRepository.getById(changeUserDataDTO.getId()))
            );
        }
    }

    private void changeLogin(String login, UserDTO user) {
        try {
            userRepository.updateLogin(login, userConverter.toEntity(user));
        } catch (Exception e) {
            LOGGER.error("Failed to update user login", e);
        }
    }

    private void changePassword(String password, UserDTO user) {
        try {
            userRepository.updatePassword(passwordEncoder.encode(password), userConverter.toEntity(user));
        } catch (Exception e) {
            LOGGER.error("Failed to update user password", e);
        }
    }

    private void changeName(String name, UserDTO user) {
        try {
            passengerRepository.updateName(name, userConverter.toEntity(user));
        } catch (Exception e) {
            LOGGER.error("Failed to update user name", e);
        }
    }

    private void changeSurname(String surname, UserDTO user) {
        try {
            passengerRepository.updateSurname(surname, userConverter.toEntity(user));
        } catch (Exception e) {
            LOGGER.error("Failed to update user surname");
        }
    }

    private void changeBirthday(LocalDate birthday, UserDTO user) {
        try {
            passengerRepository.updateBirthday(birthday, userConverter.toEntity(user));
        } catch (Exception e) {
            LOGGER.error("Failed to update user birthday");
        }
    }
}
