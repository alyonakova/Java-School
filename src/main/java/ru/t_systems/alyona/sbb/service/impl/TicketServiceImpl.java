package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
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

    @Override
    public List<TicketDTO> getAllTickets() {
        return ticketConverter.ticketListToDTOList(ticketRepository.getAll());
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
        return departureInTicket.isAfter(dateNow) &&
                (departureInTicket.toEpochMilli() - dateNow.toEpochMilli() >= MIN_TIME_TO_DEPARTURE_MILLIS);
    }

    private boolean isEnoughTickets(List<SegmentDTO> segments, int ticketsToBuy) {
        int ticketsAvailable = Integer.MAX_VALUE;
        for (SegmentDTO segment : segments) {
            ticketsAvailable = Math.min(ticketsAvailable, segment.getTicketsLeft());
        }
        return ticketsAvailable >= ticketsToBuy;
    }

    private boolean hasSamePassenger(List<SegmentDTO> segments, PassengerDTO passenger) {
        List<TicketDTO> ticketsWithSamePassenger = getByPassengerNameAndBirthday(passenger.getName(),
                passenger.getSurname(), passenger.getBirthday());
        for (TicketDTO ticket : ticketsWithSamePassenger) {
            for (SegmentDTO segmentInExistingTicket : ticket.getSegments()) {
                for (SegmentDTO segmentInNewTicket : segments) {
                    if (segmentInExistingTicket.equals(segmentInNewTicket)) return false;
                }
            }
        }
        return true;
    }

    private List<TicketDTO> getByPassengerNameAndBirthday(String name, String surname, LocalDate birthday) {
        return ticketConverter.ticketListToDTOList(ticketRepository.getByPassengerNameAndBirthday(
              name, surname, birthday
        ));
    }
}
