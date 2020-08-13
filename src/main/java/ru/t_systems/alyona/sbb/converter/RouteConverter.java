package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.RouteDTO;
import ru.t_systems.alyona.sbb.entity.RouteEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface RouteConverter {

    RouteEntity toEntity(RouteDTO routeDTO);
    RouteDTO toDTO(RouteEntity routeEntity);
    List<RouteDTO> toDTOList(List<RouteEntity> routeListEntities);
    List<RouteEntity> toEntityList(List<RouteDTO> routeListDTO);

}
