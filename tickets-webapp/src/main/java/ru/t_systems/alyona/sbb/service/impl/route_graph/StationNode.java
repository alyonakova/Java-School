package ru.t_systems.alyona.sbb.service.impl.route_graph;

import ru.t_systems.alyona.sbb.dto.StationDTO;
import ru.t_systems.alyona.sbb.service.impl.graph.Vertex;

import java.math.BigInteger;
import java.time.ZoneId;
import java.util.Collection;

public interface StationNode extends Vertex<StationNode, SegmentEdge> {

    BigInteger getId();

    String getName();

    ZoneId getTimeZone();

    @Override
    Collection<SegmentEdge> getAllOutgoingEdges();

    default StationDTO toDTO() {
        return StationDTO.builder()
                .id(getId())
                .name(getName())
                .zoneId(getTimeZone())
                .build();
    }
}
