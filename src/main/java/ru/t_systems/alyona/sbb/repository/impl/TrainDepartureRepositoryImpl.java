package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;
import ru.t_systems.alyona.sbb.entity.TrainEntity;
import ru.t_systems.alyona.sbb.repository.TrainDepartureRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
}
