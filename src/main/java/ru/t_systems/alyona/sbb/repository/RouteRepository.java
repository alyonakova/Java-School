package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.RouteEntity;
import ru.t_systems.alyona.sbb.entity.StationEntity;

import java.math.BigInteger;

@Repository
public interface RouteRepository {

    RouteEntity getById(BigInteger id);
    RouteEntity getByFromAndTo(StationEntity from, StationEntity to);

}
