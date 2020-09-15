package ru.t_systems.alyona.sbb.service.impl.route_graph.impl.db;

import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.repository.TicketSegmentRepository;
import ru.t_systems.alyona.sbb.service.impl.route_graph.RouteGraph;
import ru.t_systems.alyona.sbb.service.impl.route_graph.SegmentEdge;
import ru.t_systems.alyona.sbb.service.impl.route_graph.StationNode;

import java.math.BigInteger;
import java.time.ZoneId;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class DatabaseBackedStationNodeImpl implements StationNode {

    private final RouteGraph routeGraph;
    private final BigInteger id;
    private final StationEntity stationEntity;
    private final TicketSegmentRepository ticketSegmentRepository;

    public DatabaseBackedStationNodeImpl(RouteGraph routeGraph, BigInteger id, StationEntity stationEntity, TicketSegmentRepository ticketSegmentRepository) {
        this.routeGraph = routeGraph;
        this.id = id;
        this.stationEntity = stationEntity;
        this.ticketSegmentRepository = ticketSegmentRepository;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public String getName() {
        return getEntity().getName();
    }

    @Override
    public ZoneId getTimeZone() {
        return getEntity().getZoneId();
    }

    @Override
    public Collection<SegmentEdge> getAllOutgoingEdges() {
        return getEntity().getSegmentsTemplateFromStation().stream()
                .flatMap(segmentTemplate -> segmentTemplate.getTrain().getDepartures().stream()
                        .map(trainDeparture -> DatabaseBackedSegmentEdgeImpl.builder()
                                .graph(routeGraph)
                                .sourceStationNode(this)
                                .segmentTemplateEntity(segmentTemplate)
                                .trainDepartureEntity(trainDeparture)
                                .build()))
                .collect(toList());
    }

    public StationEntity getEntity() {
        return stationEntity;
    }

    @Override
    public String toString() {
        return "Node(" + stationEntity.getName() + ")";
    }
}
