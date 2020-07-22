package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.PassengerDTO;
import ru.t_systems.alyona.sbb.entity.PassengerEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface PassengerConverter {

    PassengerEntity passengerToEntity(PassengerDTO passengerDTO);
    PassengerDTO passengerToDTO(PassengerEntity passengerEntity);
    List<PassengerDTO> passengerListToDTOList(List<PassengerEntity> passengerListEntities);
    List<PassengerEntity> passengerListToEntityList(List<PassengerDTO> passengerListDTO);

}
