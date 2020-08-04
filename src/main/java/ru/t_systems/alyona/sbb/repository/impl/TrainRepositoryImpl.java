package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TrainEntity;
import ru.t_systems.alyona.sbb.repository.TrainRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TrainRepositoryImpl
        extends AbstractRepositoryImpl
        implements TrainRepository {

    @Override
    public TrainEntity getById(String id) {
        return getEntityManager().find(TrainEntity.class, id);
    }

    @Override
    public List<TrainEntity> getAll() {
        return getEntityManager()
                .createQuery("SELECT t FROM TrainEntity t", TrainEntity.class)
                .getResultList();
    }
}
