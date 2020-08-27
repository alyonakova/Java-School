package ru.t_systems.alyona.sbb.service.impl.route_graph;

import ru.t_systems.alyona.sbb.entity.SegmentTemplateEntity;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;
import ru.t_systems.alyona.sbb.service.impl.graph.Graph;

public interface RouteGraph extends Graph<StationNode, SegmentEdge> {

    StationNode findStationNodeByName(String stationName);

    int countBoughtTickets(SegmentTemplateEntity segmentTemplateEntity, TrainDepartureEntity trainDepartureEntity);
}
