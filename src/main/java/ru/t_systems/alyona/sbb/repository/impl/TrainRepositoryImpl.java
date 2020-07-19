package ru.t_systems.alyona.sbb.repository.impl;

import ru.t_systems.alyona.sbb.entity.TrainPO;
import ru.t_systems.alyona.sbb.repository.TrainRepository;

import javax.persistence.EntityManager;

public class TrainRepositoryImpl implements TrainRepository {

    private EntityManager em;

    public TrainRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public TrainPO getById(String id) {
        return em.find(TrainPO.class, id);
    }
}
