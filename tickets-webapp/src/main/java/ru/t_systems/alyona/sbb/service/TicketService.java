package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.BuyTicketFormDTO;
import ru.t_systems.alyona.sbb.dto.OperationResultDTO;

@Service
public interface TicketService {

    OperationResultDTO buy(BuyTicketFormDTO request);
}
