package ru.t_systems.alyona.sbb.service.impl.route_graph.impl.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.entity.SegmentTemplateEntity;
import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;
import ru.t_systems.alyona.sbb.repository.StationRepository;
import ru.t_systems.alyona.sbb.repository.TicketSegmentRepository;
import ru.t_systems.alyona.sbb.service.impl.route_graph.RouteGraph;
import ru.t_systems.alyona.sbb.service.impl.route_graph.StationNode;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class DatabaseBackedRouteGraphImpl implements RouteGraph {

    private final ConcurrentMap<String, StationNode> stationNodeMap = new ConcurrentHashMap<>();

    private final StationRepository stationRepository;
    private final TicketSegmentRepository ticketSegmentRepository;

    @Autowired
    public DatabaseBackedRouteGraphImpl(StationRepository stationRepository, TicketSegmentRepository ticketSegmentRepository) {
        this.stationRepository = stationRepository;
        this.ticketSegmentRepository = ticketSegmentRepository;
    }

    @Override
    public StationNode findStationNodeByName(String stationName) {
        return stationNodeMap.computeIfAbsent(stationName, name -> {
            StationEntity stationEntity = stationRepository.getByName(name);
            return new DatabaseBackedStationNodeImpl(this, stationEntity.getId(), stationEntity, ticketSegmentRepository);
        });
    }

    @Override
    public int countBoughtTickets(SegmentTemplateEntity segmentTemplateEntity, TrainDepartureEntity trainDepartureEntity) {
        return ticketSegmentRepository.findBySegmentTemplateIdAndTrainDepartureTime(segmentTemplateEntity.getId(), trainDepartureEntity.getDepartureTime()).size();
    }
}
