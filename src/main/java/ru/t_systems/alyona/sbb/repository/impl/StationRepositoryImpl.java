package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.repository.StationRepository;

import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StationRepositoryImpl
        extends AbstractRepositoryImpl
        implements StationRepository {

    @Override
    public StationEntity getById(BigInteger id) {
        return getEntityManager().find(StationEntity.class, id);
    }

    @Override
    public StationEntity getByName(String name) {
        TypedQuery<StationEntity> query = getEntityManager().createQuery("SELECT s FROM StationEntity s WHERE s.name = :name", StationEntity.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<StationEntity> getAll() {
        List<StationEntity> allStations = getEntityManager().createQuery(
                "SELECT s FROM StationEntity s", StationEntity.class
        ).getResultList();
        return allStations;
    }

    @Override
    public StationEntity create(StationEntity station) {
        getEntityManager().persist(station);
        return station;
    }
}
