package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import ru.t_systems.alyona.sbb.dto.TrainDTO;
import ru.t_systems.alyona.sbb.entity.TrainPO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainConverter {

    TrainPO trainToPO(TrainDTO trainDTO);
    TrainDTO trainToDTO(TrainPO trainPO);
    List<TrainDTO> trainListToDTOList(List<TrainPO> trainListPO);
    List<TrainPO> trainListToPOList(List<TrainDTO> trainListDTO);

}
