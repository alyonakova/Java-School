package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.RoutePO;
import ru.t_systems.alyona.sbb.entity.StationPO;
import ru.t_systems.alyona.sbb.repository.RouteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigInteger;

@Repository
@RequiredArgsConstructor
public class RouteRepositoryImpl implements RouteRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public RoutePO getById(BigInteger id) {
        return em.find(RoutePO.class, id);
    }

    @Override
    public RoutePO getByFromAndTo(StationPO from, StationPO to) {
        TypedQuery<RoutePO> query = em.createQuery("SELECT r FROM RoutePO r WHERE r.from = :from " +
                "AND r.to = :to", RoutePO.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        return query.getSingleResult();
    }
}
