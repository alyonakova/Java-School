package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.converter.PassengerConverter;
import ru.t_systems.alyona.sbb.dto.PassengerDTO;
import ru.t_systems.alyona.sbb.dto.PassengerWithTrainDTO;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;
import ru.t_systems.alyona.sbb.dto.TicketDTO;
import ru.t_systems.alyona.sbb.repository.PassengerRepository;
import ru.t_systems.alyona.sbb.service.PassengerService;
import ru.t_systems.alyona.sbb.service.TicketService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerConverter passengerConverter;
    private final PassengerRepository passengerRepository;
    private final TicketService ticketService;

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
                        new PassengerDTO(
                        ticket.getPassenger().getId(), ticket.getPassenger().getName(),
                                ticket.getPassenger().getSurname(), ticket.getPassenger().getBirthday()
                        ), segment.getTrain());
                passengerTrainSet.add(passengerWithTrain);
            }
        }
        return passengerTrainSet;
    }
}
