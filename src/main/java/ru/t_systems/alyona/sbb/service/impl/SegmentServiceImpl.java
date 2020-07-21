package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.RouteDTO;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;
import ru.t_systems.alyona.sbb.entity.StationPO;
import ru.t_systems.alyona.sbb.service.SegmentService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SegmentServiceImpl implements SegmentService {

    private final StationServiceImpl stationService;
    private final RouteServiceImpl routeService;

    @Override
    public List<SegmentDTO> getSegmentsByStationsAndDates(String from, String to,
                                                          Instant departure, Instant arrival) {
        StationPO stationFrom = stationService.getPOByName(from);
        StationPO stationTo = stationService.getPOByName(to);
        RouteDTO route = routeService.getByStations(stationFrom, stationTo);
        return getSegmentsByRouteAndDatesAndTickets(route, departure, arrival);
    }

    public List<SegmentDTO> getSegmentsByRouteAndDatesAndTickets(RouteDTO route, Instant departure, Instant arrival) {
        List<SegmentDTO> result = new ArrayList<>();
        List<SegmentDTO> allSegments = route.getSegments();
        for (SegmentDTO segment : allSegments) {
            if (segment.getTicketsLeft() > 0 &&
                    (departure.compareTo(segment.getDeparture()) <= 0) &&
                    (arrival.compareTo(segment.getArrival()) >= 0)) {
                result.add(segment);
            }
        }
        return result;
    }
}
