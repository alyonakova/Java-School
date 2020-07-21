package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.SegmentPO;

import java.math.BigInteger;

@Repository
public interface SegmentRepository {

    SegmentPO getById(BigInteger id);

}
