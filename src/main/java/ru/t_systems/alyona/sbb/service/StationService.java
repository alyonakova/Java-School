package ru.t_systems.alyona.sbb.service;

import ru.t_systems.alyona.sbb.entity.StationPO;

public interface StationService {

    StationPO getByName(String name);
}
