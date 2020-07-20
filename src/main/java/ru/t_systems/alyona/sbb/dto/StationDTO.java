package ru.t_systems.alyona.sbb.dto;

import lombok.Data;
import ru.t_systems.alyona.sbb.entity.RoutePO;
import ru.t_systems.alyona.sbb.entity.SegmentPO;

import java.math.BigInteger;
import java.util.List;

@Data
public class StationDTO {

    private BigInteger id;
    private String name;
    private List<RouteDTO> routesFrom;
    private List<RouteDTO> routesTo;
    private List<SegmentDTO> segmentsFromStation;
    private List<SegmentDTO> segmentsToStation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationDTO that = (StationDTO) o;
        return id.equals(that.id) &&
                name.equals(that.name) && routesFrom.equals(that.routesFrom) &&
                routesTo.equals(that.routesTo) && segmentsFromStation.equals(that.segmentsFromStation) &&
                segmentsToStation.equals(that.segmentsToStation);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (id.hashCode() + name.hashCode() + routesFrom.size() + routesTo.size() +
                segmentsFromStation.size() + segmentsToStation.size());
    }
}
