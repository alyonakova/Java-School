package ru.t_systems.alyona.sbb.repository.impl;

import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.entity.TicketSegmentEntity;
import ru.t_systems.alyona.sbb.repository.TicketSegmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

@Component
public class TicketSegmentRepositoryImpl
        extends AbstractRepositoryImpl
        implements TicketSegmentRepository {

    @Override
    public TicketSegmentEntity create(TicketSegmentEntity entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public List<TicketSegmentEntity> createAll(List<TicketSegmentEntity> entities) {
        EntityManager entityManager = getEntityManager();
        for (TicketSegmentEntity entity : entities) {
            entityManager.persist(entity);
        }
        return entities;
    }

    @Override
    public List<TicketSegmentEntity> findBySegmentTemplateIdAndTrainDepartureTime(BigInteger segmentTemplateId, Instant trainDepartureTime) {
        TypedQuery<TicketSegmentEntity> query = getEntityManager()
                .createQuery("SELECT e " +
                                "from TicketSegmentEntity e " +
                                "where e.segmentTemplate.id = :segmentTemplateId " +
                                "and e.trainDepartureTime = :trainDepartureTime",
                        TicketSegmentEntity.class);
        query.setParameter("segmentTemplateId", segmentTemplateId);
        query.setParameter("trainDepartureTime", trainDepartureTime);
        return query.getResultList();
    }
}
