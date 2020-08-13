package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.UserDTO;
import ru.t_systems.alyona.sbb.entity.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserConverter {

    UserEntity toEntity(UserDTO userDTO);
    UserDTO toDTO(UserEntity userEntity);
    List<UserDTO> toDTOList(List<UserEntity> userListEntities);
    List<UserEntity> toEntityList(List<UserDTO> userListDTO);

}
