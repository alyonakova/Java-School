package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TrainEntity;

@Repository
public interface TrainRepository {

    TrainEntity getById(String id);
}
