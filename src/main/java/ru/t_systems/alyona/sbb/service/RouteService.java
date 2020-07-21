package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.RouteDTO;
import ru.t_systems.alyona.sbb.entity.StationPO;

@Service
public interface RouteService {

    RouteDTO getByStations(StationPO from, StationPO to);
}
