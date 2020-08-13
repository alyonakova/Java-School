package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.TrainDTO;
import ru.t_systems.alyona.sbb.entity.TrainEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TrainConverter {

    TrainEntity toEntity(TrainDTO trainDTO);
    TrainDTO toDTO(TrainEntity trainEntity);
    List<TrainDTO> toDTOList(List<TrainEntity> trainListEntities);
    List<TrainEntity> toEntityList(List<TrainDTO> trainListDTO);

}
