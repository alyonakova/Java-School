package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.PassengerEntity;
import ru.t_systems.alyona.sbb.entity.UserEntity;
import ru.t_systems.alyona.sbb.repository.PassengerRepository;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PassengerRepositoryImpl
        extends AbstractRepositoryImpl
        implements PassengerRepository {

    @Override
    public PassengerEntity getById(BigInteger id) {
        return getEntityManager().find(PassengerEntity.class, id);
    }

    @Override
    public List<PassengerEntity> getAll() {
        return getEntityManager()
                .createQuery("SELECT p FROM PassengerEntity p",
                        PassengerEntity.class)
                .getResultList();
    }

    @Override
    public PassengerEntity create(PassengerEntity passenger) {
        getEntityManager().persist(passenger);
        return passenger;
    }

    @Override
    public void updateName(String name, UserEntity user) {
        Query query = getEntityManager().createQuery("UPDATE PassengerEntity p SET p.name = :name WHERE p = :passenger");
        query.setParameter("name", name);
        query.setParameter("passenger", user.getPassenger());
        query.executeUpdate();
    }

}
