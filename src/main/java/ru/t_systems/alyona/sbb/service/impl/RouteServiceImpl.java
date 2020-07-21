package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.converter.RouteConverter;
import ru.t_systems.alyona.sbb.dto.RouteDTO;
import ru.t_systems.alyona.sbb.entity.StationPO;
import ru.t_systems.alyona.sbb.repository.impl.RouteRepositoryImpl;
import ru.t_systems.alyona.sbb.service.RouteService;

@RequiredArgsConstructor
@Service
public class RouteServiceImpl implements RouteService {

    private final RouteConverter routeConverter;
    private final RouteRepositoryImpl routeRepository;

    @Override
    public RouteDTO getByStations(StationPO from, StationPO to) {
        return routeConverter.routeToDTO(routeRepository.getByFromAndTo(from, to));
    }
}
