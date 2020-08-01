package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.t_systems.alyona.sbb.entity.UserEntity;
import ru.t_systems.alyona.sbb.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Override
    @Transactional
    public void updateLogin(String login, UserEntity user) {
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE UserEntity u SET u.login = :login WHERE u = :user");
        query.setParameter("login", login);
        query.setParameter("user", user);
        query.executeUpdate();
        em.getTransaction().commit();
    }
}
