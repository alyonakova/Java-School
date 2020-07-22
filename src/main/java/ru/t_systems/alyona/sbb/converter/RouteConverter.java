package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.dto.RouteDTO;
import ru.t_systems.alyona.sbb.entity.RouteEntity;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface RouteConverter {

    RouteEntity routeToEntity(RouteDTO routeDTO);
    RouteDTO routeToDTO(RouteEntity routeEntity);
    List<RouteDTO> routeListToDTOList(List<RouteEntity> routeListEntities);
    List<RouteEntity> routeListToEntityList(List<RouteDTO> routeListDTO);

}
