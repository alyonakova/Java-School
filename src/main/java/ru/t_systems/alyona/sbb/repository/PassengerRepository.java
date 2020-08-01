package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.PassengerEntity;
import ru.t_systems.alyona.sbb.entity.UserEntity;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PassengerRepository {

    PassengerEntity getById(BigInteger id);
    List<PassengerEntity> getAll();
    PassengerEntity create(PassengerEntity passenger);
    void updateName(String name, UserEntity passenger);
}
