package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.PassengerDTO;
import ru.t_systems.alyona.sbb.dto.PassengerWithTrainDTO;
import ru.t_systems.alyona.sbb.dto.TicketDTO;

import java.util.List;
import java.util.Set;

@Service
public interface PassengerService {

    List<PassengerDTO> getAllPassengers();
    Set<PassengerWithTrainDTO> getPassengersWithTrains();
}
