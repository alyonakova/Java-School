package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TicketPO;
import ru.t_systems.alyona.sbb.repository.TicketRepository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@Repository
@RequiredArgsConstructor
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
