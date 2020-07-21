package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.RoutePO;
import ru.t_systems.alyona.sbb.entity.StationPO;

import java.math.BigInteger;

@Repository
public interface RouteRepository {

    RoutePO getById(BigInteger id);
    RoutePO getByFromAndTo(StationPO from, StationPO to);

}
