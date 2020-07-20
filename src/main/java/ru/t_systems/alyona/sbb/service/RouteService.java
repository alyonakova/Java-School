package ru.t_systems.alyona.sbb.service;

import ru.t_systems.alyona.sbb.dto.RouteDTO;
import ru.t_systems.alyona.sbb.entity.StationPO;

public interface RouteService {

    RouteDTO getByStations(StationPO from, StationPO to);
}
