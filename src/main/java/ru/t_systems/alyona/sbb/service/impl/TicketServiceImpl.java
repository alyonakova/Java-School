package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t_systems.alyona.sbb.controller.ConnectionCache;
import ru.t_systems.alyona.sbb.converter.PassengerConverter;
import ru.t_systems.alyona.sbb.converter.TicketConverter;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.entity.PassengerEntity;
import ru.t_systems.alyona.sbb.entity.TicketEntity;
import ru.t_systems.alyona.sbb.entity.TicketSegmentEntity;
import ru.t_systems.alyona.sbb.repository.PassengerRepository;
import ru.t_systems.alyona.sbb.repository.SegmentTemplateRepository;
import ru.t_systems.alyona.sbb.repository.TicketRepository;
import ru.t_systems.alyona.sbb.repository.TicketSegmentRepository;
import ru.t_systems.alyona.sbb.service.TicketService;

import javax.inject.Provider;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    public static final Duration ALLOWED_TIME_BEFORE_TRAIN_DEPARTURE = Duration.ofMinutes(10);

    private final TicketConverter ticketConverter;
    private final TicketRepository ticketRepository;
    private final Provider<ConnectionCache> connectionCacheProvider;
    private final PassengerRepository passengerRepository;
    private final PassengerConverter passengerConverter;
    private final TicketSegmentRepository ticketSegmentRepository;
    private final SegmentTemplateRepository segmentTemplateRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    public List<TicketDTO> getAllTickets() {
        List<TicketDTO> result = null;
        try {
            result = ticketConverter.toDTOList(ticketRepository.getAll());
        } catch (Exception e) {
            LOGGER.error("Failed to get all existing tickets", e);
        }
        return result;
    }

    private boolean isEnoughTickets(List<TicketSegmentDTO> segments, int ticketsToBuy) {
            int ticketsAvailable = segments.stream()
                    .mapToInt(segment -> {
                        int boughtTicketsCount = ticketSegmentRepository
                                .findBySegmentTemplateIdAndTrainDepartureTime(
                                        segment.getSegmentTemplateId(), segment.getTrainDepartureTime()
                                ).size();
                        return segment.getTrainCapacity() - boughtTicketsCount;
                    })
                    .min()
                    .orElse(0);
            return ticketsAvailable >= ticketsToBuy;
    }

    private boolean hasSamePassenger(List<TicketSegmentDTO> newTicketSegments, PassengerDTO passenger) {
        try {
            List<TicketDTO> ticketsWithSamePassenger = getByPassengerNameAndBirthday(
                    passenger.getName(), passenger.getSurname(), passenger.getBirthday());

            for (TicketDTO ticket : ticketsWithSamePassenger) {
                for (TicketSegmentDTO existingSegment : ticket.getSegments()) {
                    for (TicketSegmentDTO newSegment : newTicketSegments) {
                        if (areSameSegments(existingSegment, newSegment)) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to check if there is the same passenger in new ticket segments", e);
        }
        return false;
    }

    private boolean areSameSegments(TicketSegmentDTO segment1, TicketSegmentDTO segment2) {
        return Objects.equals(segment1.getSegmentTemplateId(), segment2.getSegmentTemplateId()) &&
                Objects.equals(segment1.getTrainDepartureTime(), segment2.getTrainDepartureTime());
    }

    private List<TicketDTO> getByPassengerNameAndBirthday(String name, String surname, LocalDate birthday) {
        List<TicketDTO> result = null;
        try {
            result = ticketConverter.toDTOList(ticketRepository.getByPassengerNameAndBirthday(
                    name, surname, birthday
            ));
        } catch (Exception e) {
            LOGGER.error("Failed to get tickets of passenger with given name, surname and birthday", e);
        }
        return result;
    }

    @Override
    @Transactional
    public OperationResultDTO buy(BuyTicketFormDTO request) {

        ConnectionDTO connection = connectionCacheProvider.get().findById(request.getConnectionId());

        if (Duration.between(ZonedDateTime.now(), connection.getDepartureTime())
                .compareTo(ALLOWED_TIME_BEFORE_TRAIN_DEPARTURE) < 0) {
            return OperationResultDTO.error("You cannot buy a ticket which is less than in 10 minutes from now.");
        }

        List<TicketSegmentDTO> segments = connectionToSegments(connection);

        //check if enough tickets left
        int numberOfTicketsToBuy = request.getPassengers().size();
        if (!isEnoughTickets(segments, numberOfTicketsToBuy)) {
            return OperationResultDTO.error("Not enough tickets left!");
        }

        for (PassengerDTO passenger : request.getPassengers()) {

            //check if passenger is already registered for route
            if (hasSamePassenger(segments, passenger)) {
                return OperationResultDTO.error("Passenger " + passenger.getName() + " " + passenger.getSurname() +
                        " is already registered for this route.");
            }

            //Create passenger in DB if he is not there
            PassengerEntity existingPassenger = passengerRepository.getByNameAndSurnameAndBirthday(
                    passenger.getName(), passenger.getSurname(), passenger.getBirthday()
            ).orElse(null);

            if (existingPassenger == null) {
                existingPassenger = passengerRepository.create(passengerConverter.toEntity(passenger));
            }

            TicketDTO newTicket = TicketDTO.builder()
                    .passenger(passengerConverter.toDTO(existingPassenger))
                    .segments(segments)
                    .build();
            createTicket(newTicket);

        }

        return OperationResultDTO.successful("Tickets successfully purchased.");
    }

    private List<TicketSegmentDTO> connectionToSegments(ConnectionDTO connection) {
        List<TicketSegmentDTO> segments = new ArrayList<>();
        int indexInTicket = 0;
        for (ChainDTO chain : connection.getChains()) {
            for (SegmentDTO segment : chain.getSegments()) {
                segments.add(TicketSegmentDTO.builder()
                        .ticketId(null) // no ticket is created yet
                        .indexInTicket(indexInTicket)
                        .segmentTemplateId(segment.getSegmentTemplateId())
                        .trainDepartureTime(segment.getTrainDepartureTime())
                        .trainCapacity(segment.getTrain().getSeatsCount())
                        .build());
                indexInTicket++;
            }
        }
        return segments;
    }

    private void createTicket(TicketDTO ticket) {
        try {
            TicketEntity ticketEntity = ticketRepository.create(ticketConverter.toEntity(ticket));

            for (TicketSegmentDTO segment : ticket.getSegments()) {

                ticketSegmentRepository.create(TicketSegmentEntity.builder()
                        .ticket(ticketEntity)
                        .segmentTemplate(segmentTemplateRepository.findById(segment.getSegmentTemplateId()))
                        .trainDepartureTime(segment.getTrainDepartureTime())
                        .indexInTicket(segment.getIndexInTicket())
                        .build());
            }
        } catch (Exception e) {
            LOGGER.error("Failed to create a new ticket", e);
        }
    }

}
