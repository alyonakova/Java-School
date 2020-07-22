package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.StationDTO;
import ru.t_systems.alyona.sbb.entity.StationEntity;

@Service
public interface StationService {

    StationEntity getEntityByName(String name);

    StationDTO getDTOByName(String name);
}
