package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.BuyTicketFormDTO;
import ru.t_systems.alyona.sbb.dto.OperationResultDTO;
import ru.t_systems.alyona.sbb.dto.TicketDTO;

import java.util.List;

@Service
public interface TicketService {

    List<TicketDTO> getAllTickets();
    OperationResultDTO buy(BuyTicketFormDTO request);
}
