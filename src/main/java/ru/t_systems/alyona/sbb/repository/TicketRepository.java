package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TicketEntity;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface TicketRepository {

    TicketEntity getById(BigInteger id);
    List<TicketEntity> getAll();
}
