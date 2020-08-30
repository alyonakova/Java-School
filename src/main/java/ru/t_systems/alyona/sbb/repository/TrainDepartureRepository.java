package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;
import ru.t_systems.alyona.sbb.entity.TrainEntity;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Repository
public interface TrainDepartureRepository {

    TrainDepartureEntity create(TrainDepartureEntity entity);

    Iterable<TrainDepartureEntity> create(Collection<TrainDepartureEntity> entities);

    List<TrainDepartureEntity> getDeparturesByTrain(TrainEntity train);

    void cancelAllTrainDepartures(TrainEntity train);

    void restoreAllTrainDepartures(TrainEntity train);

    void delayAllTrainDepartures(TrainEntity train, int delayInMinutes);

    TrainDepartureEntity getTrainDeparture(TrainEntity train, Instant departureTime);
}
