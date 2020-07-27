package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.PassengerEntity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PassengerRepository {

    PassengerEntity getById(BigInteger id);
    List<PassengerEntity> getAll();
    PassengerEntity create(PassengerEntity passenger);
}
