package ru.t_systems.alyona.sbb.repository.impl;

import ru.t_systems.alyona.sbb.entity.RoutePO;
import ru.t_systems.alyona.sbb.entity.StationPO;
import ru.t_systems.alyona.sbb.repository.RouteRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigInteger;

public class RouteRepositoryImpl implements RouteRepository {

    private EntityManager em;

    public RouteRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public RoutePO getById(BigInteger id) {
        return em.find(RoutePO.class, id);
    }

    @Override
    public RoutePO getByFromAndTo(StationPO from, StationPO to) {
        BigInteger idFrom = from.getId();
        BigInteger idTo = to.getId();
        TypedQuery<RoutePO> query = em.createQuery("SELECT r FROM RoutePO r WHERE r.from = :from " +
                "AND r.to = :to", RoutePO.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        return query.getSingleResult();
    }
}
