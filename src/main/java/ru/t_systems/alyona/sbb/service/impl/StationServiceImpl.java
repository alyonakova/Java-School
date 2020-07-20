package ru.t_systems.alyona.sbb.service.impl;

import lombok.Data;
import ru.t_systems.alyona.sbb.converter.StationConverter;
import ru.t_systems.alyona.sbb.entity.StationPO;
import ru.t_systems.alyona.sbb.repository.impl.StationRepositoryImpl;
import ru.t_systems.alyona.sbb.service.StationService;

@Data
public class StationServiceImpl implements StationService {

    private StationConverter stationConverter;
    private StationRepositoryImpl stationRepository;

    @Override
    public StationPO getByName(String name) {
        return stationRepository.getByName(name);
    }
}
