package ru.t_systems.alyona.sbb.repository;

import ru.t_systems.alyona.sbb.entity.TrainPO;

public interface TrainRepository {

    TrainPO getById(String id);
}
