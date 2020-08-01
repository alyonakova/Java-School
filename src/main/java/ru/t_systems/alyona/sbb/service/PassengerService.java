package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.ChangeUserDataDTO;
import ru.t_systems.alyona.sbb.dto.PassengerDTO;
import ru.t_systems.alyona.sbb.dto.PassengerWithTrainDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public interface PassengerService {

    List<PassengerDTO> getAllPassengers();
    Set<PassengerWithTrainDTO> getPassengersWithTrains();
    PassengerDTO createPassenger(String name, String surname, LocalDate birthday);
    void updatePassengerData(ChangeUserDataDTO changeUserDataDTO);
}
