package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.converter.StationConverter;
import ru.t_systems.alyona.sbb.dto.StationDTO;
import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.repository.StationRepository;
import ru.t_systems.alyona.sbb.service.StationService;

@RequiredArgsConstructor
@Service
public class StationServiceImpl implements StationService {

    private final StationConverter stationConverter;
    private final StationRepository stationRepository;

    @Override
    public StationEntity getEntityByName(String name) {
        return stationRepository.getByName(name);
    }

    @Override
    public StationDTO getDTOByName(String name) {
        return stationConverter.stationToDTO(stationRepository.getByName(name));
    }
}
