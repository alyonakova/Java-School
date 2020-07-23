package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.PassengerDTO;

import java.util.List;

@Service
public interface PassengerService {

    List<PassengerDTO> getAllPassengers();
}
