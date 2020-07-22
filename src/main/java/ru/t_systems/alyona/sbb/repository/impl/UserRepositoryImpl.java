package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.UserEntity;
import ru.t_systems.alyona.sbb.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public UserEntity getById(BigInteger id) {
        return em.find(UserEntity.class, id);
    }
}
