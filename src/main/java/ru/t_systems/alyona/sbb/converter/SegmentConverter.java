package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;
import ru.t_systems.alyona.sbb.entity.SegmentEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SegmentConverter {

    SegmentEntity toEntity(SegmentDTO segmentDTO);
    SegmentDTO toDTO(SegmentEntity segmentEntity);
    List<SegmentDTO> toDTOList(List<SegmentEntity> segmentListEntities);
    List<SegmentEntity> toEntityList(List<SegmentDTO> segmentListDTO);

}
