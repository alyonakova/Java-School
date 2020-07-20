package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import ru.t_systems.alyona.sbb.dto.TicketDTO;
import ru.t_systems.alyona.sbb.entity.TicketPO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketConverter {

    TicketPO ticketToPO(TicketDTO ticketDTO);
    TicketDTO ticketToDTO(TicketPO ticketPO);
    List<TicketDTO> ticketListToDTOList(List<TicketPO> ticketListPO);
    List<TicketPO> ticketListToPOList(List<TicketDTO> ticketListDTO);

}