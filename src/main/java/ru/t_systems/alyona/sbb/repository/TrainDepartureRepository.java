package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;
import ru.t_systems.alyona.sbb.entity.TrainEntity;

import java.util.Collection;
import java.util.List;

@Repository
public interface TrainDepartureRepository {

    TrainDepartureEntity create(TrainDepartureEntity entity);

    Iterable<TrainDepartureEntity> create(Collection<TrainDepartureEntity> entities);

    List<TrainDepartureEntity> getDeparturesByTrain(TrainEntity train);
}
