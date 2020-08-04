package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.t_systems.alyona.sbb.entity.UserEntity;
import ru.t_systems.alyona.sbb.repository.UserRepository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl
        extends AbstractRepositoryImpl
        implements UserRepository {

    @Override
    public UserEntity getById(BigInteger id) {
        return getEntityManager().find(UserEntity.class, id);
    }

    @Override
    @Transactional
    public UserEntity create(UserEntity user) {
//        if (user.getId() == null) {
        getEntityManager().persist(user);
//        } else {
//            user = em.merge(user);
//        }
        return user;
    }

    @Override
    public UserEntity getByLogin(String login) {
        TypedQuery<UserEntity> query = getEntityManager().createQuery(
                "SELECT u FROM UserEntity u WHERE u.login = :login",
                UserEntity.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

    @Override
    public void updateLogin(String newLogin, UserEntity user) {
        Query query = getEntityManager().createQuery("UPDATE UserEntity u SET u.login = :login WHERE u = :user");
        query.setParameter("login", newLogin);
        query.setParameter("user", user);
        query.executeUpdate();
    }
}
