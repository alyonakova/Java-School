package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TrainEntity;
import ru.t_systems.alyona.sbb.repository.TrainRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class TrainRepositoryImpl implements TrainRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public TrainEntity getById(String id) {
        return em.find(TrainEntity.class, id);
    }
}
