package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.StationPO;
import ru.t_systems.alyona.sbb.repository.StationRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigInteger;

@Repository
@RequiredArgsConstructor
public class StationRepositoryImpl implements StationRepository {

    private EntityManager em;

    public StationRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public StationPO getById(BigInteger id) {
        return em.find(StationPO.class, id);
    }

    @Override
    public StationPO getByName(String name) {
        TypedQuery<StationPO> query = em.createQuery("SELECT s FROM StationPO s WHERE s.name = :name", StationPO.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
