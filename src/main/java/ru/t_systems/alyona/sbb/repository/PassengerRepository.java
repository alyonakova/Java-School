package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.PassengerPO;

import java.math.BigInteger;

@Repository
public interface PassengerRepository {

    PassengerPO getById(BigInteger id);
}
