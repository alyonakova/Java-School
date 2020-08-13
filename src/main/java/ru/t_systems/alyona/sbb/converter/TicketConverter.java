package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.TicketDTO;
import ru.t_systems.alyona.sbb.entity.TicketEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TicketConverter {

    TicketEntity toEntity(TicketDTO ticketDTO);
    TicketDTO toDTO(TicketEntity ticketEntity);
    List<TicketDTO> toDTOList(List<TicketEntity> ticketListEntities);
    List<TicketEntity> toEntityList(List<TicketDTO> ticketListDTO);

}
