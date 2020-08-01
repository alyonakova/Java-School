package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.UserEntity;

import java.math.BigInteger;

@Repository
public interface UserRepository {

    UserEntity getById(BigInteger id);
    UserEntity create(UserEntity userEntity);
    UserEntity getByLogin(String login);
    void updateLogin(String login, UserEntity user);
}
