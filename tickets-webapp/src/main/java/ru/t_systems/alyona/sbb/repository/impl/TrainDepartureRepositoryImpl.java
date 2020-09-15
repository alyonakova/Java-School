package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;
import ru.t_systems.alyona.sbb.entity.TrainEntity;
import ru.t_systems.alyona.sbb.exceptions.SBBApplicationException;
import ru.t_systems.alyona.sbb.repository.TrainDepartureRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TrainDepartureRepositoryImpl
        extends AbstractRepositoryImpl
        implements TrainDepartureRepository {

    @Override
    public TrainDepartureEntity create(TrainDepartureEntity entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public Iterable<TrainDepartureEntity> create(Collection<TrainDepartureEntity> entities) {
        EntityManager entityManager = getEntityManager();
        for (TrainDepartureEntity entity : entities) {
            entityManager.persist(entity);
        }
        return entities;
    }

    @Override
    public List<TrainDepartureEntity> getDeparturesByTrain(TrainEntity train) {
        TypedQuery<TrainDepartureEntity> query = getEntityManager().createQuery(
                "SELECT t FROM TrainDepartureEntity t WHERE t.train = :train",
                TrainDepartureEntity.class);
        query.setParameter("train", train);
        return query.getResultList();
    }

    @Override
    public void cancelAllTrainDepartures(TrainEntity train) {
        Query query = getEntityManager().createQuery(
                "UPDATE TrainDepartureEntity t SET t.cancelled = true WHERE t.train = :train");
        query.setParameter("train", train);
        query.executeUpdate();
    }

    @Override
    public void restoreAllTrainDepartures(TrainEntity train) {
        Query query = getEntityManager().createQuery(
                "UPDATE TrainDepartureEntity t SET t.cancelled = false WHERE t.train = :train"
        );
        query.setParameter("train", train);
        query.executeUpdate();
    }

    @Override
    public void delayAllTrainDepartures(TrainEntity train, int delayInMinutes) {
        Query query = getEntityManager().createQuery(
                "UPDATE TrainDepartureEntity t SET t.delayInMinutes = :delayInMinutes WHERE t.train = :train"
        );
        query.setParameter("delayInMinutes", delayInMinutes);
        query.setParameter("train", train);
        query.executeUpdate();
    }

    @Override
    public TrainDepartureEntity getTrainDeparture(TrainEntity train, Instant departureTime) {
        TypedQuery<TrainDepartureEntity> query = getEntityManager().createQuery(
                "SELECT t FROM TrainDepartureEntity t WHERE t.train = :train AND t.departureTime = :departureTime",
                TrainDepartureEntity.class
        );
        query.setParameter("train", train);
        query.setParameter("departureTime", departureTime);
        return query.getResultList().stream().findFirst()
                .orElseThrow(() -> new SBBApplicationException(
                        "There is no departure at " + departureTime + " for train '" + train.getId() + "'"));
    }

    @Override
    public void cancelTrainDeparture(TrainDepartureEntity trainDeparture) {
        Query query = getEntityManager().createQuery(
                "UPDATE TrainDepartureEntity t SET t.cancelled = true WHERE t = :trainDeparture"
        );
        query.setParameter("trainDeparture", trainDeparture);
        query.executeUpdate();
    }

    @Override
    public void restoreTrainDeparture(TrainDepartureEntity trainDeparture) {
        Query query = getEntityManager().createQuery(
                "UPDATE TrainDepartureEntity t SET t.cancelled = false WHERE t = :trainDeparture"
        );
        query.setParameter("trainDeparture", trainDeparture);
        query.executeUpdate();
    }

    @Override
    public void delayTrainDeparture(TrainDepartureEntity trainDeparture, int delayInMinutes) {
        Query query = getEntityManager().createQuery(
                "UPDATE TrainDepartureEntity t SET t.delayInMinutes = :delayInMinutes WHERE t = :trainDeparture"
        );
        query.setParameter("delayInMinutes", delayInMinutes);
        query.setParameter("trainDeparture", trainDeparture);
        query.executeUpdate();
    }
}
