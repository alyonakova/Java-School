package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;
import ru.t_systems.alyona.sbb.entity.SegmentEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SegmentConverter {

    SegmentEntity segmentToEntity(SegmentDTO segmentDTO);
    SegmentDTO segmentToDTO(SegmentEntity segmentEntity);
    List<SegmentDTO> segmentListToDTOList(List<SegmentEntity> segmentListEntities);
    List<SegmentEntity> segmentListToEntityList(List<SegmentDTO> segmentListDTO);

}
