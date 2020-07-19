package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import ru.t_systems.alyona.sbb.dto.UserDTO;
import ru.t_systems.alyona.sbb.entity.UserPO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserConverter {

    UserPO userToPO(UserDTO userDTO);
    UserDTO userToDTO(UserPO userPO);
    List<UserDTO> userListToDTOList(List<UserPO> userListPO);
    List<UserPO> userListToPOList(List<UserDTO> userListDTO);

}
