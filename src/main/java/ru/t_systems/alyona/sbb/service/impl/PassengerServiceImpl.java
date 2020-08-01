package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.converter.PassengerConverter;
import ru.t_systems.alyona.sbb.converter.UserConverter;
import ru.t_systems.alyona.sbb.dto.*;
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

    @Override
    public List<PassengerDTO> getAllPassengers() {
        return passengerConverter.passengerListToDTOList(passengerRepository.getAll());
    }

    @Override
    public Set<PassengerWithTrainDTO> getPassengersWithTrains() {
        List<TicketDTO> allTickets = ticketService.getAllTickets();
        Set<PassengerWithTrainDTO> passengerTrainSet = new HashSet<>();
        for (TicketDTO ticket : allTickets) {
            for (SegmentDTO segment : ticket.getSegments()) {
                PassengerWithTrainDTO passengerWithTrain = new PassengerWithTrainDTO(
                        ticket.getPassenger(), segment.getTrain());
                passengerTrainSet.add(passengerWithTrain);
            }
        }
        return passengerTrainSet;
    }

    @Override
    public PassengerDTO createPassenger(String name, String surname, LocalDate birthday) {
        return passengerConverter.passengerToDTO(passengerRepository.create(
                passengerConverter.passengerToEntity(new PassengerDTO(null, name, surname, birthday))
        ));
    }

    @Override
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
            //change name
        } else if (changeUserDataDTO.getNewSurname() != null) {
            //change surname
        } else if (changeUserDataDTO.getNewBirthday() != null) {
            //change birthday
        }
    }

    private void changeLogin(String login, UserDTO user) {
        userRepository.updateLogin(login, userConverter.userToEntity(user));
    }
    private void changePassword() {}
    private void changeName() {}
    private void changeSurname() {}
    private void changeBirthday() {}
}
