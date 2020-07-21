package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.UserPO;

import java.math.BigInteger;

@Repository
public interface UserRepository {

    UserPO getById(BigInteger id);
}
