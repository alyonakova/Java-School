package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.TrainDTO;
import ru.t_systems.alyona.sbb.entity.TrainEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TrainConverter {

    TrainEntity trainToEntity(TrainDTO trainDTO);
    TrainDTO trainToDTO(TrainEntity trainEntity);
    List<TrainDTO> trainListToDTOList(List<TrainEntity> trainListEntities);
    List<TrainEntity> trainListToEntityList(List<TrainDTO> trainListDTO);

}
