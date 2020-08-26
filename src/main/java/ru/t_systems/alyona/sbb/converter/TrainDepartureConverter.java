package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.TrainDepartureDTO;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TrainDepartureConverter {

    TrainDepartureEntity toEntity(TrainDepartureDTO trainDepartureDTO);
    TrainDepartureDTO toDTO(TrainDepartureEntity trainDepartureEntity);
    List<TrainDepartureDTO> toDTOList(List<TrainDepartureEntity> trainDepartureListEntities);
    List<TrainDepartureEntity> toEntityList(List<TrainDepartureDTO> trainDepartureListDTO);

}
