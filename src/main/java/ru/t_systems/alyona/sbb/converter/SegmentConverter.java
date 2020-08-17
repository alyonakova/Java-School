package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;
import ru.t_systems.alyona.sbb.entity.SegmentEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SegmentConverter {

    @Mappings({
            @Mapping(target = "arrival", source = "segmentDTO.arrivalTime"),
            @Mapping(target = "departure", source = "segmentDTO.departureTime"),
    })
    SegmentEntity toEntity(SegmentDTO segmentDTO);

    @Mappings({
            @Mapping(target = "arrivalTime", source = "segmentEntity.arrival"),
            @Mapping(target = "departureTime", source = "segmentEntity.departure")
    })
    SegmentDTO toDTO(SegmentEntity segmentEntity);

    List<SegmentDTO> toDTOList(List<SegmentEntity> segmentListEntities);

    List<SegmentEntity> toEntityList(List<SegmentDTO> segmentListDTO);

}
