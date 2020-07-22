package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.RouteDTO;
import ru.t_systems.alyona.sbb.entity.StationEntity;

@Service
public interface RouteService {

    RouteDTO getByStations(StationEntity from, StationEntity to);
}
