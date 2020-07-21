package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TrainPO;
import ru.t_systems.alyona.sbb.repository.TrainRepository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
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
