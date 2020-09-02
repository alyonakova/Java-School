package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.TicketSegmentDTO;
import ru.t_systems.alyona.sbb.entity.TicketSegmentEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TicketSegmentConverter {

    @Mappings(
            @Mapping(target = "segmentTemplateId", source = "entity.segmentTemplate.id")
    )
    TicketSegmentDTO toDTO(TicketSegmentEntity entity);

    TicketSegmentEntity toEntity(TicketSegmentDTO dto);

    List<TicketSegmentDTO> toDTOList(List<TicketSegmentEntity> entities);

    List<TicketSegmentEntity> toEntityList(List<TicketSegmentDTO> dtos);
}
