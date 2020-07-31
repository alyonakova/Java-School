package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TicketEntity;
import ru.t_systems.alyona.sbb.repository.TicketRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public TicketEntity getById(BigInteger id) {
        return em.find(TicketEntity.class, id);
    }

    @Override
    public List<TicketEntity> getAll() {
        List<TicketEntity> allTickets = em.createQuery(
                "SELECT t FROM TicketEntity t", TicketEntity.class
        ).getResultList();
        return allTickets;
    }

    @Override
    public List<TicketEntity> getByPassengerNameAndBirthday(String name, String surname, LocalDate birthday) {
        TypedQuery<TicketEntity> query = em.createQuery(
                "SELECT t FROM TicketEntity t WHERE t.passenger.name = :name AND " +
                        "t.passenger.surname = :surname AND t.passenger.birthday = :birthday",
                TicketEntity.class);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        query.setParameter("birthday", birthday);
        List<TicketEntity> tickets = query.getResultList();
        return tickets;
    }
}
