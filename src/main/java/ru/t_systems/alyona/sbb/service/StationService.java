package ru.t_systems.alyona.sbb.service;

import ru.t_systems.alyona.sbb.dto.StationDTO;

public interface StationService {

    StationDTO getByName(String name);
}
