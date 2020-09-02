package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.SegmentTemplateEntity;
import ru.t_systems.alyona.sbb.entity.TrainEntity;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@Repository
public interface SegmentTemplateRepository {

    SegmentTemplateEntity findById(BigInteger segmentTemplateId);

    SegmentTemplateEntity create(SegmentTemplateEntity segmentTemplate);

    Iterable<SegmentTemplateEntity> create(Collection<SegmentTemplateEntity> segmentTemplates);

    List<SegmentTemplateEntity> getByTrain(TrainEntity train);
}
