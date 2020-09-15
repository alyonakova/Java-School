package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t_systems.alyona.sbb.converter.PassengerConverter;
import ru.t_systems.alyona.sbb.converter.TrainConverter;
import ru.t_systems.alyona.sbb.converter.UserConverter;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.entity.PassengerEntity;
import ru.t_systems.alyona.sbb.entity.TicketEntity;
import ru.t_systems.alyona.sbb.entity.TicketSegmentEntity;
import ru.t_systems.alyona.sbb.entity.UserEntity;
import ru.t_systems.alyona.sbb.repository.PassengerRepository;
import ru.t_systems.alyona.sbb.repository.TicketRepository;
import ru.t_systems.alyona.sbb.repository.UserRepository;
import ru.t_systems.alyona.sbb.service.PassengerService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService {

    private final PassengerConverter passengerConverter;
    private final PassengerRepository passengerRepository;
    private final TrainConverter trainConverter;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Set<PassengerWithTrainDTO> getPassengersWithTrains() {
        List<TicketEntity> allTickets = ticketRepository.getAll();
        Set<PassengerWithTrainDTO> passengerTrainSet = new HashSet<>();
        try {
            for (TicketEntity ticket : allTickets) {
                for (TicketSegmentEntity segment : ticket.getSegments()) {
                    PassengerWithTrainDTO passengerWithTrain = new PassengerWithTrainDTO(
                            passengerConverter.toDTO(ticket.getPassenger()),
                            trainConverter.toDTO(segment.getSegmentTemplate().getTrain()));
                    passengerTrainSet.add(passengerWithTrain);
                }
            }
        } catch (Exception e) {
            log.error("Failed to get passengers with their trains", e);
        }
        return passengerTrainSet;
    }

    @Override
    public PassengerDTO createPassenger(String name, String surname, LocalDate birthday) {
        PassengerDTO passenger = null;
        try {
            final PassengerEntity newPassenger = passengerConverter.toEntity(
                    new PassengerDTO(null, name, surname, birthday));
            final PassengerEntity createdPassenger = passengerRepository.create(newPassenger);
            passenger = passengerConverter.toDTO(createdPassenger);
        } catch (Exception e) {
            log.error("Failed to create new passenger", e);
        }
        return passenger;
    }

    @Override
    @Transactional
    public OperationResultDTO updatePassengerData(ChangeUserDataDTO changeUserDataDTO) {
        if (changeUserDataDTO.getNewLogin() != null) {

            UserEntity existingUser = userRepository.getByLogin(changeUserDataDTO.getNewLogin());
            if (existingUser != null) {
                return OperationResultDTO.error("The specified login is already exists");
            }

            changeLogin(
                    changeUserDataDTO.getNewLogin(),
                    userConverter.toDTO(userRepository.getById(changeUserDataDTO.getUserId()))
            );
            return OperationResultDTO.successful("Login is successfully updated");

        } else if (changeUserDataDTO.getNewPassword() != null) {
            changePassword(
                    changeUserDataDTO.getNewPassword(),
                    userConverter.toDTO(userRepository.getById(changeUserDataDTO.getUserId()))
            );
            return OperationResultDTO.successful("Password is successfully updated");

        } else if (changeUserDataDTO.getNewName() != null) {
            changeName(
                    changeUserDataDTO.getNewName(),
                    userConverter.toDTO(userRepository.getById(changeUserDataDTO.getUserId()))
            );
            return OperationResultDTO.successful("Name is successfully updated");

        } else if (changeUserDataDTO.getNewSurname() != null) {
            changeSurname(
                    changeUserDataDTO.getNewSurname(),
                    userConverter.toDTO(userRepository.getById(changeUserDataDTO.getUserId()))
            );
            return OperationResultDTO.successful("Surname is successfully updated");

        } else if (changeUserDataDTO.getNewBirthday() != null) {
            changeBirthday(
                    changeUserDataDTO.getNewBirthday(),
                    userConverter.toDTO(userRepository.getById(changeUserDataDTO.getUserId()))
            );
            return OperationResultDTO.successful("Birthday is successfully updated");
        }
        return OperationResultDTO.technicalError("Nothing to update");
    }

    private void changeLogin(String login, UserDTO user) {
        try {
            userRepository.updateLogin(login, userConverter.toEntity(user));
        } catch (Exception e) {
            log.error("Failed to update user login", e);
        }
    }

    private void changePassword(String password, UserDTO user) {
        try {
            userRepository.updatePassword(passwordEncoder.encode(password), userConverter.toEntity(user));
        } catch (Exception e) {
            log.error("Failed to update user password", e);
        }
    }

    private void changeName(String name, UserDTO user) {
        try {
            passengerRepository.updateName(name, userConverter.toEntity(user));
        } catch (Exception e) {
            log.error("Failed to update user name", e);
        }
    }

    private void changeSurname(String surname, UserDTO user) {
        try {
            passengerRepository.updateSurname(surname, userConverter.toEntity(user));
        } catch (Exception e) {
            log.error("Failed to update user surname");
        }
    }

    private void changeBirthday(LocalDate birthday, UserDTO user) {
        try {
            passengerRepository.updateBirthday(birthday, userConverter.toEntity(user));
        } catch (Exception e) {
            log.error("Failed to update user birthday");
        }
    }
}
