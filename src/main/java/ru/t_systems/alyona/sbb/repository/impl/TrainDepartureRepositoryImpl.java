package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;
import ru.t_systems.alyona.sbb.repository.TrainDepartureRepository;

import javax.persistence.EntityManager;
import java.util.Collection;

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
}
