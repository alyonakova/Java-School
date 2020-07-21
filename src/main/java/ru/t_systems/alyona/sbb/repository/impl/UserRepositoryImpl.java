package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.UserPO;
import ru.t_systems.alyona.sbb.repository.UserRepository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private EntityManager em;

    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public UserPO getById(BigInteger id) {
        return em.find(UserPO.class, id);
    }
}
