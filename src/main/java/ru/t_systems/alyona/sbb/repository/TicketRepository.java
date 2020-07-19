package ru.t_systems.alyona.sbb.repository;

import ru.t_systems.alyona.sbb.entity.TicketPO;

import java.math.BigInteger;

public interface TicketRepository {

    TicketPO getById(BigInteger id);
}
