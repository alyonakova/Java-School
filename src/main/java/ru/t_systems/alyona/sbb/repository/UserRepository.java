package ru.t_systems.alyona.sbb.repository;

import ru.t_systems.alyona.sbb.entity.UserPO;

import java.math.BigInteger;

public interface UserRepository {

    UserPO getById(BigInteger id);
}
