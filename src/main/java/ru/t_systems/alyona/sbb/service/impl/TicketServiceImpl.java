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
        try {
            int ticketsAvailable = segments.stream()
                    .mapToInt(segment -> {
                        int boughtTicketsCount = ticketSegmentRepository.findBySegmentTemplateIdAndTrainDepartureTime(segment.getSegmentTemplateId(), segment.getTrainDepartureTime()).size();
                        int remainingTickets = segment.getTrainCapacity() - boughtTicketsCount;
                        return remainingTickets;
                    })
                    .min()
                    .orElse(0);
            return ticketsAvailable >= ticketsToBuy;
        } catch (Exception e) {
            return true; // FIXME: We don't need try-catch here at all
        }
    }

    private boolean hasSamePassenger(List<TicketSegmentDTO> segments, PassengerDTO passenger) {
        try {
            List<TicketDTO> ticketsWithSamePassenger = getByPassengerNameAndBirthday(passenger.getName(),
                    passenger.getSurname(), passenger.getBirthday());
            for (TicketDTO ticket : ticketsWithSamePassenger) {
                for (TicketSegmentDTO segmentInExistingTicket : ticket.getSegments()) {
                    for (TicketSegmentDTO segmentInNewTicket : segments) {
                        if (segmentInExistingTicket.equals(segmentInNewTicket)) {
                            return false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to check if there is the same passenger in new ticket segments", e);
        }
        return true;
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

        int numberOfTicketsToBuy = request.getPassengers().size();
        if (!isEnoughTickets(segments, numberOfTicketsToBuy)) {
            return OperationResultDTO.error("Not enough tickets left!");
        }

        // TODO: Check that passengers are not already registered to these trains

        for (PassengerDTO passenger : request.getPassengers()) {

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
                        .ticketId(null) // FIXME
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
            // TODO: Create all TicketSegment entities at once, not in a loop
            //ticketSegmentRepository.createAll(ticketSegmentConverter.toEntityList(ticket.getSegments()));
            for (TicketSegmentDTO segment : ticket.getSegments()) {
                // FIXME: This conversion is ugly, maybe move to TicketSegmentConverter
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
