package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.TicketSegmentDTO;
import ru.t_systems.alyona.sbb.entity.TicketSegmentEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TicketSegmentConverter {

    TicketSegmentDTO toDTO(TicketSegmentEntity entity);

    TicketSegmentEntity toEntity(TicketSegmentDTO dto);

    List<TicketSegmentDTO> toDTOList(List<TicketSegmentEntity> entities);

    List<TicketSegmentEntity> toEntityList(List<TicketSegmentDTO> dtos);
}
