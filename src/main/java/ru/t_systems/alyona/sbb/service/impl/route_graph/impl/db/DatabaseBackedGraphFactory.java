package ru.t_systems.alyona.sbb.service.impl.route_graph.impl.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.t_systems.alyona.sbb.repository.StationRepository;
import ru.t_systems.alyona.sbb.repository.TicketSegmentRepository;

@Component
public class DatabaseBackedGraphFactory {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    TicketSegmentRepository ticketSegmentRepository;

    public DatabaseBackedRouteGraphImpl createGraph() {
        return new DatabaseBackedRouteGraphImpl(stationRepository, ticketSegmentRepository);
    }
}
