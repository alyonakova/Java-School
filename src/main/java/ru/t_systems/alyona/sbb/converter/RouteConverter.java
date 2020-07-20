package ru.t_systems.alyona.sbb.converter;

import org.mapstruct.Mapper;
import ru.t_systems.alyona.sbb.dto.RouteDTO;
import ru.t_systems.alyona.sbb.entity.RoutePO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RouteConverter {

    RoutePO routeToPO(RouteDTO routeDTO);
    RouteDTO routeToDTO(RoutePO routePO);
    List<RouteDTO> routeListToDTOList(List<RoutePO> routeListPO);
    List<RoutePO> routeListToPOList(List<RouteDTO> routeListDTO);

}