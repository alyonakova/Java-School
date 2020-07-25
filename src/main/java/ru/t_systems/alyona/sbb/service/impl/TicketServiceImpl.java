package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.converter.TicketConverter;
import ru.t_systems.alyona.sbb.dto.TicketDTO;
import ru.t_systems.alyona.sbb.repository.TicketRepository;
import ru.t_systems.alyona.sbb.service.TicketService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketConverter ticketConverter;
    private final TicketRepository ticketRepository;

    @Override
    public List<TicketDTO> getAllTickets() {
        return ticketConverter.ticketListToDTOList(ticketRepository.getAll());
    }
}
