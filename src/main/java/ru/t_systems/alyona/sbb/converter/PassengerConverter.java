package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import ru.t_systems.alyona.sbb.dto.PassengerDTO;
import ru.t_systems.alyona.sbb.entity.PassengerPO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassengerConverter {

    PassengerPO passengerToPO(PassengerDTO passengerDTO);
    PassengerDTO passengerToDTO(PassengerPO passengerPO);
    List<PassengerDTO> passengerListToDTOList(List<PassengerPO> passengerListPO);
    List<PassengerPO> passengerListToPOList(List<PassengerDTO> passengerListDTO);

}
