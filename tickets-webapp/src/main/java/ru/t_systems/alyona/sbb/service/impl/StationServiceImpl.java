package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t_systems.alyona.sbb.converter.StationConverter;
import ru.t_systems.alyona.sbb.dto.OperationResultDTO;
import ru.t_systems.alyona.sbb.dto.StationDTO;
import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.repository.StationRepository;
import ru.t_systems.alyona.sbb.service.StationService;

@RequiredArgsConstructor
@Service
@Slf4j
public class StationServiceImpl implements StationService {

    private final StationConverter stationConverter;
    private final StationRepository stationRepository;

    @Override
    @Transactional
    public OperationResultDTO createStation(StationDTO station) {
        try {
            StationEntity existingStation = stationRepository.getByName(station.getName());
            if (existingStation != null) {
                return OperationResultDTO.error("Station with name " + station.getName() + " is already exists");
            }
            stationRepository.create(stationConverter.toEntity(station));
        } catch (Exception e) {
            log.error("Failed to create a new station", e);
            return OperationResultDTO.error("Failed to create a new station");
        }
        return OperationResultDTO.successful("Station successfully created.");
    }
}
