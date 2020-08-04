package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.RouteEntity;
import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.repository.RouteRepository;

import javax.persistence.TypedQuery;
import java.math.BigInteger;

@Repository
@RequiredArgsConstructor
public class RouteRepositoryImpl
        extends AbstractRepositoryImpl
        implements RouteRepository {

    @Override
    public RouteEntity getById(BigInteger id) {
        return getEntityManager().find(RouteEntity.class, id);
    }

    @Override
    public RouteEntity getByFromAndTo(StationEntity from, StationEntity to) {
        TypedQuery<RouteEntity> query = getEntityManager()
                .createQuery("SELECT r FROM RouteEntity r " +
                        "WHERE r.from = :from " +
                        "AND r.to = :to", RouteEntity.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        return query.getSingleResult();
    }
}
