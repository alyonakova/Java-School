package ru.t_systems.alyona.sbb.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.t_systems.alyona.sbb.entity.TicketEntity;
import ru.t_systems.alyona.sbb.repository.TicketRepository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl
        extends AbstractRepositoryImpl
        implements TicketRepository {

    @Override
    public TicketEntity getById(BigInteger id) {
        return getEntityManager().find(TicketEntity.class, id);
    }

    @Override
    public List<TicketEntity> getAll() {
        return getEntityManager()
                .createQuery("SELECT t FROM TicketEntity t", TicketEntity.class)
                .getResultList();
    }

    @Override
    public List<TicketEntity> getByPassengerNameAndBirthday(String name, String surname, LocalDate birthday) {
        return getEntityManager()
                .createQuery(
                        "SELECT t FROM TicketEntity t " +
                                "WHERE t.passenger.name = :name " +
                                "AND t.passenger.surname = :surname " +
                                "AND t.passenger.birthday = :birthday",
                        TicketEntity.class)
                .setParameter("name", name)
                .setParameter("surname", surname)
                .setParameter("birthday", birthday)
                .getResultList();
    }
}
