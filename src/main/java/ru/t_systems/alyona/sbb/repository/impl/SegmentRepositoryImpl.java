package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.SegmentPO;
import ru.t_systems.alyona.sbb.repository.SegmentRepository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@Repository
@RequiredArgsConstructor
public class SegmentRepositoryImpl implements SegmentRepository {

    private EntityManager em;

    public SegmentRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public SegmentPO getById(BigInteger id) {
        return em.find(SegmentPO.class, id);
    }

}
