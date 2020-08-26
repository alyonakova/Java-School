package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.SegmentTemplateEntity;
import ru.t_systems.alyona.sbb.entity.TrainEntity;
import ru.t_systems.alyona.sbb.repository.SegmentTemplateRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SegmentTemplateRepositoryImpl extends AbstractRepositoryImpl
        implements SegmentTemplateRepository {

    @Override
    public SegmentTemplateEntity findById(BigInteger id) {
        return getEntityManager().find(SegmentTemplateEntity.class, id);
    }

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

    @Override
    public List<SegmentTemplateEntity> getByTrain(TrainEntity train) {
        TypedQuery<SegmentTemplateEntity> query = getEntityManager().createQuery(
                "SELECT s FROM SegmentTemplateEntity s WHERE s.train = :train",
                SegmentTemplateEntity.class);
        query.setParameter("train", train);
        return query.getResultList();
    }
}
