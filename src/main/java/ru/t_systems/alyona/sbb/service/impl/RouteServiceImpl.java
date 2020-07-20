package ru.t_systems.alyona.sbb.service.impl;

import lombok.Data;
import ru.t_systems.alyona.sbb.converter.RouteConverter;
import ru.t_systems.alyona.sbb.converter.StationConverter;
import ru.t_systems.alyona.sbb.dto.RouteDTO;
import ru.t_systems.alyona.sbb.entity.StationPO;
import ru.t_systems.alyona.sbb.repository.impl.RouteRepositoryImpl;
import ru.t_systems.alyona.sbb.service.RouteService;

@Data
public class RouteServiceImpl implements RouteService {

    private RouteConverter routeConverter;
    private StationConverter stationConverter;
    private RouteRepositoryImpl routeRepository;

    @Override
    public RouteDTO getByStations(StationPO from, StationPO to) {
        return routeConverter.routeToDTO(routeRepository.getByFromAndTo(from, to));
    }
}
