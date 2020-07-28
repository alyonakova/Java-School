package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TicketEntity;
import ru.t_systems.alyona.sbb.entity.TrainEntity;
import ru.t_systems.alyona.sbb.repository.TrainRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TrainRepositoryImpl implements TrainRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public TrainEntity getById(String id) {
        return em.find(TrainEntity.class, id);
    }

    @Override
    public List<TrainEntity> getAll() {
        List<TrainEntity> allTrains = em.createQuery(
                "SELECT t FROM TrainEntity t", TrainEntity.class
        ).getResultList();
        return allTrains;
    }
}
