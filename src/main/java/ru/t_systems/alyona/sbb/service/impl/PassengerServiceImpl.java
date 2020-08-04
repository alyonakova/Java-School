package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PassengerServiceImpl.class);

    @Override
    public List<PassengerDTO> getAllPassengers() {
        List<PassengerDTO> result = null;
        try {
            result = passengerConverter.passengerListToDTOList(passengerRepository.getAll());
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
            final PassengerEntity newPassanger = passengerConverter.passengerToEntity(
                    new PassengerDTO(null, name, surname, birthday));
            final PassengerEntity createdPassenger = passengerRepository.create(newPassanger);
            passenger = passengerConverter.passengerToDTO(createdPassenger);
        } catch (Exception e) {
            LOGGER.error("Failed to create new passenger", e);
        }
        return passenger;
    }

    @Override
    @Transactional
    public void updatePassengerData(ChangeUserDataDTO changeUserDataDTO) {
        //TODO
        if (changeUserDataDTO.getNewLogin() != null) {
            changeLogin(
                    changeUserDataDTO.getNewLogin(),
                    userConverter.userToDTO(userRepository.getByLogin(changeUserDataDTO.getLogin()))
            );
        } else if (changeUserDataDTO.getNewPassword() != null) {
            //change password
        } else if (changeUserDataDTO.getNewName() != null) {
            changeName(
                    changeUserDataDTO.getNewName(),
                    userConverter.userToDTO(userRepository.getByLogin(changeUserDataDTO.getLogin()))
            );
        } else if (changeUserDataDTO.getNewSurname() != null) {
            //change surname
        } else if (changeUserDataDTO.getNewBirthday() != null) {
            //change birthday
        }
    }

    private void changeLogin(String login, UserDTO user) {
        try {
            userRepository.updateLogin(login, userConverter.userToEntity(user));
        } catch (Exception e) {
            LOGGER.error("Failed to update user login", e);
        }
    }

    private void changePassword() {
    }

    private void changeName(String name, UserDTO user) {
        try {
            passengerRepository.updateName(name, userConverter.userToEntity(user));
        } catch (Exception e) {
            LOGGER.error("Failed to update user name", e);
        }
    }

    private void changeSurname() {
    }

    private void changeBirthday() {
    }
}
