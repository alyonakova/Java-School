package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.*;

@Service
public interface TicketService {

    OperationResultDTO buy(BuyTicketFormDTO request);
}
