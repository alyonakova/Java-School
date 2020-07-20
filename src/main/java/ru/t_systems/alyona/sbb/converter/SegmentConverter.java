package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;
import ru.t_systems.alyona.sbb.entity.SegmentPO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SegmentConverter {

    SegmentPO segmentToPO(SegmentDTO segmentDTO);
    SegmentDTO segmentToDTO(SegmentPO segmentPO);
    List<SegmentDTO> segmentListToDTOList(List<SegmentPO> segmentListPO);
    List<SegmentPO> segmentListToPOList(List<SegmentDTO> segmentListDTO);

}