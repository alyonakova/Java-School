package ru.t_systems.alyona.sbb.service.impl.route_graph;

import ru.t_systems.alyona.sbb.dto.SegmentDTO;
import ru.t_systems.alyona.sbb.dto.TrainDTO;
import ru.t_systems.alyona.sbb.service.impl.graph.Edge;

import java.time.ZonedDateTime;

public interface SegmentEdge extends Edge<StationNode, SegmentEdge> {

    ZonedDateTime getDepartureTime();

    ZonedDateTime getArrivalTime();

    int getPriceFranks();

    int getAvailableTicketsCount();

    String getTrainNumber();

    SegmentDTO toDTO();
}
