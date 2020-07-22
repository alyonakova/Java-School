package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TicketEntity;
import ru.t_systems.alyona.sbb.repository.TicketRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public TicketEntity getById(BigInteger id) {
        return em.find(TicketEntity.class, id);
    }
}
