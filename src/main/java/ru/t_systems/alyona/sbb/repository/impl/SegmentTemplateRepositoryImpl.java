package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.SegmentTemplateEntity;
import ru.t_systems.alyona.sbb.repository.SegmentTemplateRepository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SegmentTemplateRepositoryImpl extends AbstractRepositoryImpl
        implements SegmentTemplateRepository {

    @Override
    public SegmentTemplateEntity create(SegmentTemplateEntity segmentTemplate) {
        getEntityManager().persist(segmentTemplate);
        return segmentTemplate;
    }

    @Override
    public Iterable<SegmentTemplateEntity> create(Collection<SegmentTemplateEntity> segmentTemplates) {
        EntityManager entityManager = getEntityManager();
        for (SegmentTemplateEntity entity : segmentTemplates) {
            entityManager.persist(entity);
        }
        return segmentTemplates;
    }
}
