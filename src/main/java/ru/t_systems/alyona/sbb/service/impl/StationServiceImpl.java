package ru.t_systems.alyona.sbb.service.impl;

import lombok.Data;
import ru.t_systems.alyona.sbb.converter.StationConverter;
import ru.t_systems.alyona.sbb.dto.StationDTO;
import ru.t_systems.alyona.sbb.repository.impl.StationRepositoryImpl;
import ru.t_systems.alyona.sbb.service.StationService;

@Data
public class StationServiceImpl implements StationService {

    private StationConverter stationConverter;
    private StationRepositoryImpl stationRepository;

    @Override
    public StationDTO getByName(String name) {
        return stationConverter.stationToDTO(stationRepository.getByName(name));
    }
}
