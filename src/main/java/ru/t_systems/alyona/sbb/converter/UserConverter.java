package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.UserDTO;
import ru.t_systems.alyona.sbb.entity.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserConverter {

    UserEntity userToEntity(UserDTO userDTO);
    UserDTO userToDTO(UserEntity userEntity);
    List<UserDTO> userListToDTOList(List<UserEntity> userListEntities);
    List<UserEntity> userListToEntityList(List<UserDTO> userListDTO);

}
