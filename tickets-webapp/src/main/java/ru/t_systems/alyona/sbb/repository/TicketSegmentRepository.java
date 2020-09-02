package ru.t_systems.alyona.sbb.repository;

import ru.t_systems.alyona.sbb.entity.TicketSegmentEntity;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

public interface TicketSegmentRepository {


    TicketSegmentEntity create(TicketSegmentEntity entity);

    List<TicketSegmentEntity> createAll(List<TicketSegmentEntity> toEntityList);

    List<TicketSegmentEntity> findBySegmentTemplateIdAndTrainDepartureTime(
            BigInteger segmentTemplateId,
            Instant trainDepartureTime);
}
