package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.StationDTO;
import ru.t_systems.alyona.sbb.entity.StationEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface StationConverter {

    StationEntity toEntity(StationDTO stationDTO);
    StationDTO toDTO(StationEntity stationEntity);
    List<StationDTO> toDTOList(List<StationEntity> stationListEntities);
    List<StationEntity> toEntityList(List<StationDTO> stationListDTO);

}
