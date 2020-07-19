package ru.t_systems.alyona.sbb.repository.impl;

import ru.t_systems.alyona.sbb.entity.TicketPO;
import ru.t_systems.alyona.sbb.repository.TicketRepository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

public class TicketRepositoryImpl implements TicketRepository {

    private EntityManager em;

    public TicketRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public TicketPO getById(BigInteger id) {
        return em.find(TicketPO.class, id);
    }
}
