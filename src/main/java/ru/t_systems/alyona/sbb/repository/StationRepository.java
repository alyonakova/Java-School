package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.StationPO;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface StationRepository {

    StationPO getById(BigInteger id);
    StationPO getByName(String name);
}
