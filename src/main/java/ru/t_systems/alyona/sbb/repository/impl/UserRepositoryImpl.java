package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.entity.UserEntity;
import ru.t_systems.alyona.sbb.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    @Override
    public UserEntity create(UserEntity user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            user = em.merge(user);
        }
        return user;
    }

    @Override
    public UserEntity getByLogin(String login) {
        TypedQuery<UserEntity> query = em.createQuery(
                "SELECT u FROM UserEntity u WHERE u.login = :login",
                UserEntity.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }
}
