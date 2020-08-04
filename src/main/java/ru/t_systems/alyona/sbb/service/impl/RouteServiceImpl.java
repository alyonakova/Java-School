package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.converter.RouteConverter;
import ru.t_systems.alyona.sbb.dto.RouteDTO;
import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.repository.RouteRepository;
import ru.t_systems.alyona.sbb.service.RouteService;

@RequiredArgsConstructor
@Service
public class RouteServiceImpl implements RouteService {

    private final RouteConverter routeConverter;
    private final RouteRepository routeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Override
    public RouteDTO getByStations(StationEntity from, StationEntity to) {
        RouteDTO route = null;
        try {
            route = routeConverter.routeToDTO(routeRepository.getByFromAndTo(from, to));
        } catch (Exception e) {
            LOGGER.error("Failed to get route by two stations");
        }
        return route;
    }
}
