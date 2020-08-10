package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;

import java.util.Collection;

@Repository
public interface TrainDepartureRepository {

    TrainDepartureEntity create(TrainDepartureEntity entity);

    Iterable<TrainDepartureEntity> create(Collection<TrainDepartureEntity> entities);
}
