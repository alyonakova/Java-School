package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import ru.t_systems.alyona.sbb.dto.StationDTO;
import ru.t_systems.alyona.sbb.entity.StationPO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationConverter {

    StationPO stationToPO(StationDTO stationDTO);
    StationDTO stationToDTO(StationPO stationPO);
    List<StationDTO> stationListToDTOList(List<StationPO> stationListPO);
    List<StationPO> stationListToPOList(List<StationDTO> stationListDTO);

}
