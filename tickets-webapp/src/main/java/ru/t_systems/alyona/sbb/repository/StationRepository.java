package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.StationEntity;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface StationRepository {

    StationEntity getById(BigInteger id);

    StationEntity getByName(String name);

    List<StationEntity> getAll();

    StationEntity create(StationEntity station);
}
