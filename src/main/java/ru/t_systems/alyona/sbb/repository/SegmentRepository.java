package ru.t_systems.alyona.sbb.repository;

import ru.t_systems.alyona.sbb.entity.SegmentPO;

import java.math.BigInteger;

public interface SegmentRepository {

    SegmentPO getById(BigInteger id);

}
