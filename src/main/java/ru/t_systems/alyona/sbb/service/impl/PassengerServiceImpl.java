package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.converter.PassengerConverter;
import ru.t_systems.alyona.sbb.dto.PassengerDTO;
import ru.t_systems.alyona.sbb.repository.PassengerRepository;
import ru.t_systems.alyona.sbb.service.PassengerService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerConverter passengerConverter;
    private final PassengerRepository passengerRepository;

    @Override
    public List<PassengerDTO> getAllPassengers() {
        return passengerConverter.passengerListToDTOList(passengerRepository.getAll());
    }
}
