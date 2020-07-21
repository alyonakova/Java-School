package ru.t_systems.alyona.sbb.repository;

import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TicketPO;

import java.math.BigInteger;

@Repository
public interface TicketRepository {

    TicketPO getById(BigInteger id);
}
