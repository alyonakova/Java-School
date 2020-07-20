package ru.t_systems.alyona.sbb.repository.impl;

import ru.t_systems.alyona.sbb.entity.SegmentPO;
import ru.t_systems.alyona.sbb.repository.SegmentRepository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

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
