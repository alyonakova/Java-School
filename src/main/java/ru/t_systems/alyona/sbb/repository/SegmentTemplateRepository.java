package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.SegmentTemplateEntity;

import java.math.BigInteger;
import java.util.Collection;

@Repository
public interface SegmentTemplateRepository {

    SegmentTemplateEntity findById(BigInteger segmentTemplateId);

    SegmentTemplateEntity create(SegmentTemplateEntity segmentTemplate);

    Iterable<SegmentTemplateEntity> create(Collection<SegmentTemplateEntity> segmentTemplates);
}
