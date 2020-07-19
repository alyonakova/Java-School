package ru.t_systems.alyona.sbb.repository.impl;

import ru.t_systems.alyona.sbb.entity.PassengerPO;
import ru.t_systems.alyona.sbb.repository.PassengerRepository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

public class PassengerRepositoryImpl implements PassengerRepository {

    private EntityManager em;

    public PassengerRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public PassengerPO getById(BigInteger id) {
        return em.find(PassengerPO.class, id);
    }
}
