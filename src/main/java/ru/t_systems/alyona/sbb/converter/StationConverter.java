package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.StationDTO;
import ru.t_systems.alyona.sbb.entity.StationEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface StationConverter {

    StationEntity stationToEntity(StationDTO stationDTO);
    StationDTO stationToDTO(StationEntity stationEntity);
    List<StationDTO> stationListToDTOList(List<StationEntity> stationListEntities);
    List<StationEntity> stationListToEntityList(List<StationDTO> stationListDTO);

}
