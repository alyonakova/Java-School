package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.PassengerEntity;
import ru.t_systems.alyona.sbb.repository.PassengerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PassengerRepositoryImpl implements PassengerRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public PassengerEntity getById(BigInteger id) {
        return em.find(PassengerEntity.class, id);
    }

    @Override
    public List<PassengerEntity> getAll() {
        List<PassengerEntity> allPassengers = em.createQuery(
                "SELECT p FROM PassengerEntity p", PassengerEntity.class
        ).getResultList();
        return allPassengers;
    }
}
