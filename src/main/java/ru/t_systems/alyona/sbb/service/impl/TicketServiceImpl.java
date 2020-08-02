package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.converter.TicketConverter;
import ru.t_systems.alyona.sbb.dto.PassengerDTO;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;
import ru.t_systems.alyona.sbb.dto.SegmentsGroupDTO;
import ru.t_systems.alyona.sbb.dto.TicketDTO;
import ru.t_systems.alyona.sbb.repository.TicketRepository;
import ru.t_systems.alyona.sbb.service.TicketService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketConverter ticketConverter;
    private final TicketRepository ticketRepository;

    private final long MIN_TIME_TO_DEPARTURE_MILLIS = 10 * 60000;

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    public List<TicketDTO> getAllTickets() {
        List<TicketDTO> result = null;
        try {
            result = ticketConverter.ticketListToDTOList(ticketRepository.getAll());
        } catch (Exception e) {
            LOGGER.error("Failed to get all existing tickets", e);
        }
        return result;
    }

    public boolean checkPossibilityToBuyTicket(SegmentsGroupDTO segments, List<PassengerDTO> passengers) {
        int ticketsToBuy = passengers.size();
        if (isCorrectDate(segments.getSegments().get(0).getDeparture()) &&
        isEnoughTickets(segments.getSegments(), ticketsToBuy)) {
            for (PassengerDTO passenger : passengers) {
                if (hasSamePassenger(segments.getSegments(), passenger)) return false;
            }
        } else return false;
        return true;
    }

    private boolean isCorrectDate(Instant departureInTicket) {
        Instant dateNow = Instant.now();
        boolean result = false;
        try {
            result = departureInTicket.isAfter(dateNow) &&
                    (departureInTicket.toEpochMilli() - dateNow.toEpochMilli() >= MIN_TIME_TO_DEPARTURE_MILLIS);
        } catch (Exception e)  {
            LOGGER.error("Failed to check if it's' more than 10 minutes to departure", e);
        }
        return result;
    }

    private boolean isEnoughTickets(List<SegmentDTO> segments, int ticketsToBuy) {
        int ticketsAvailable = Integer.MAX_VALUE;
        try {
            for (SegmentDTO segment : segments) {
                ticketsAvailable = Math.min(ticketsAvailable, segment.getTicketsLeft());
            }
        } catch (Exception e) {
            LOGGER.error("Failed to check if enough tickets left", e);
        }
        return ticketsAvailable >= ticketsToBuy;
    }

    private boolean hasSamePassenger(List<SegmentDTO> segments, PassengerDTO passenger) {
        try {
            List<TicketDTO> ticketsWithSamePassenger = getByPassengerNameAndBirthday(passenger.getName(),
                    passenger.getSurname(), passenger.getBirthday());
            for (TicketDTO ticket : ticketsWithSamePassenger) {
                for (SegmentDTO segmentInExistingTicket : ticket.getSegments()) {
                    for (SegmentDTO segmentInNewTicket : segments) {
                        if (segmentInExistingTicket.equals(segmentInNewTicket)) return false;
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
            result = ticketConverter.ticketListToDTOList(ticketRepository.getByPassengerNameAndBirthday(
                    name, surname, birthday
            ));
        } catch (Exception e) {
            LOGGER.error("Failed to get tickets of passenger with given name, surname and birthday", e);
        }
        return result;
    }
}
