package ru.t_systems.alyona.sbb.service.impl;

import one.util.streamex.StreamEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t_systems.alyona.sbb.dto.ChainDTO;
import ru.t_systems.alyona.sbb.dto.ConnectionDTO;
import ru.t_systems.alyona.sbb.dto.ConnectionSearchQueryDTO;
import ru.t_systems.alyona.sbb.dto.ConnectionSearchResultDTO;
import ru.t_systems.alyona.sbb.service.ConnectionSearchService;
import ru.t_systems.alyona.sbb.service.impl.graph.Path;
import ru.t_systems.alyona.sbb.service.impl.graph.algo.DepthFirstSearch;
import ru.t_systems.alyona.sbb.service.impl.route_graph.RouteGraph;
import ru.t_systems.alyona.sbb.service.impl.route_graph.SegmentEdge;
import ru.t_systems.alyona.sbb.service.impl.route_graph.StationNode;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class ConnectionSearchServiceImpl implements ConnectionSearchService {

    @Autowired
    RouteGraph routeGraph;

    @Override
    @Transactional
    public ConnectionSearchResultDTO findConnections(ConnectionSearchQueryDTO request) {

        if (request.getDepartureStationName().equals(request.getArrivalStationName()))
            return ConnectionSearchResultDTO.error("Departure and arrival stations must be different!");

        RouteGraph routeGraph = getRouteGraph();

        StationNode departureNode = null;
        StationNode arrivalNode;

        try {

            departureNode = routeGraph.findStationNodeByName(request.getDepartureStationName());
            arrivalNode = routeGraph.findStationNodeByName(request.getArrivalStationName());

        } catch (EmptyResultDataAccessException e) {
            String wrongStationName = (departureNode == null) ?
                    request.getDepartureStationName() : request.getArrivalStationName();
            return ConnectionSearchResultDTO.error("No station with name “" + wrongStationName + "” found.");
        }

        ZonedDateTime requestedDepartureTime = request.getDepartureTime().atZone(departureNode.getTimeZone());
        ZonedDateTime requestedArrivalTime = request.getArrivalTime().atZone(arrivalNode.getTimeZone());

        Collection<Path<StationNode, SegmentEdge>> paths = new DepthFirstSearch()
                .findAllSimplePaths(departureNode, arrivalNode, (nextSegment, currentPath) -> {
                    var minimumDepartureTime = (currentPath.isEmpty() ? requestedDepartureTime : currentPath.getLastEdge().getArrivalTime());
                    return isSegmentInTimeRange(nextSegment, minimumDepartureTime, requestedArrivalTime);
                });

        if (paths.size() == 0) {
            return ConnectionSearchResultDTO.error("No routes found.");
        }

        return ConnectionSearchResultDTO.builder()
                .successful(true)
                .discoveredConnections(convertPathsToConnectionList(paths))
                .build();
    }

    private boolean isSegmentInTimeRange(SegmentEdge nextSegment, ZonedDateTime requestedDepartureTime, ZonedDateTime requestedArrivalTime) {
        return nextSegment.getDepartureTime().isAfter(requestedDepartureTime) &&
                nextSegment.getArrivalTime().isBefore(requestedArrivalTime);
    }

    private List<ConnectionDTO> convertPathsToConnectionList(Collection<Path<StationNode, SegmentEdge>> paths) {
        return paths.stream()
                .map(this::convertPathToConnection)
                .collect(toList());
    }

    private ConnectionDTO convertPathToConnection(Path<StationNode, SegmentEdge> path) {
        List<SegmentEdge> segments = path.getEdges();
        ZonedDateTime departureTime = segments.get(0).getDepartureTime();
        ZonedDateTime arrivalTime = segments.get(segments.size() - 1).getArrivalTime();
        return ConnectionDTO.builder()
                .id(UUID.randomUUID())
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .priceFranks(segments.stream().mapToInt(SegmentEdge::getPriceFranks).sum())
                .availableTicketsCount(segments.stream().mapToInt(SegmentEdge::getAvailableTicketsCount).min().orElse(0))
                .chains(groupSegmentsToChains(segments))
                .build();
    }

    private List<ChainDTO> groupSegmentsToChains(List<SegmentEdge> segments) {
        // Group adjacent segments by train number.
        return StreamEx.of(segments)
                .groupRuns((segment1, segment2) ->
                        Objects.equals(
                                segment1.getTrainNumber(),
                                segment2.getTrainNumber()))
                .map(this::convertSegmentGroupToChain)
                .toList();
    }

    private ChainDTO convertSegmentGroupToChain(List<SegmentEdge> segmentGroup) {
        SegmentEdge lastSegment = segmentGroup.get(segmentGroup.size() - 1);
        SegmentEdge firstSegment = segmentGroup.get(0);

        return ChainDTO.builder()
                .trainNumber(firstSegment.getTrainNumber())
                .segments(segmentGroup.stream().map(SegmentEdge::toDTO).collect(toList()))
                .departureStation(firstSegment.getSource().toDTO())
                .departureTime(firstSegment.getDepartureTime())
                .arrivalStation(lastSegment.getTarget().toDTO())
                .arrivalTime(lastSegment.getArrivalTime())
                .build();
    }

    private RouteGraph getRouteGraph() {
        return routeGraph;
    }
}
