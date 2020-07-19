package ru.t_systems.alyona.sbb.repository.impl;

import ru.t_systems.alyona.sbb.entity.RoutePO;
import ru.t_systems.alyona.sbb.entity.SegmentPO;
import ru.t_systems.alyona.sbb.repository.SegmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
