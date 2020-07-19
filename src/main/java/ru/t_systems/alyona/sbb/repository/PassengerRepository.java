package ru.t_systems.alyona.sbb.repository;

import ru.t_systems.alyona.sbb.entity.PassengerPO;

import java.math.BigInteger;

public interface PassengerRepository {

    PassengerPO getById(BigInteger id);
}
