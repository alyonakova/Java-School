package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.SegmentEntity;
import ru.t_systems.alyona.sbb.repository.SegmentRepository;

import java.math.BigInteger;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SegmentRepositoryImpl
        extends AbstractRepositoryImpl
        implements SegmentRepository {

    @Override
    public SegmentEntity getById(BigInteger id) {
        return getEntityManager().find(SegmentEntity.class, id);
    }

    @Override
    public List<SegmentEntity> getAll() {
        List<SegmentEntity> allSegments = getEntityManager().createQuery(
                "SELECT s FROM SegmentEntity s", SegmentEntity.class
        ).getResultList();
        return allSegments;
    }

}
