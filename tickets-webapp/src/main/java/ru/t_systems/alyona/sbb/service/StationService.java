package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.OperationResultDTO;
import ru.t_systems.alyona.sbb.dto.StationDTO;

import java.util.List;

@Service
public interface StationService {

    OperationResultDTO createStation(StationDTO station);

    List<StationDTO> getAllStations();
}
