package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.StationDTO;
import ru.t_systems.alyona.sbb.entity.StationPO;

@Service
public interface StationService {

    StationPO getPOByName(String name);

    StationDTO getDTOByName(String name);
}
