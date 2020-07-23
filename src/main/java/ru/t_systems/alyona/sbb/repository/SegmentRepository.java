package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.SegmentEntity;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface SegmentRepository {

    SegmentEntity getById(BigInteger id);
    List<SegmentEntity> getAll();

}
