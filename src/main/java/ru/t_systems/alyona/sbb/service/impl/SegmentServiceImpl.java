package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.RouteDTO;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;
import ru.t_systems.alyona.sbb.entity.StationPO;
import ru.t_systems.alyona.sbb.service.SegmentService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SegmentServiceImpl implements SegmentService {

    private final StationServiceImpl stationService;
    private final RouteServiceImpl routeService;

    @Override
    public List<List<SegmentDTO>> getSegmentGroupsByStationsAndDates(String from, String to,
                                                                     Instant departure, Instant arrival) {
        StationPO stationFrom = stationService.getPOByName(from);
        StationPO stationTo = stationService.getPOByName(to);
        RouteDTO route = routeService.getByStations(stationFrom, stationTo);
        return getSortedSegmentGroups(
                getSegmentsByRouteAndDatesAndTickets(route, departure, arrival)
        );
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

    public List<List<SegmentDTO>> getSortedSegmentGroups(List<SegmentDTO> segments) {
        //sort list by trains
        segments.sort(Comparator.comparing(s -> s.getTrain().getId()));
        //group segments
        List<List<SegmentDTO>> segmentGroups = separateIntoGroups(segments);
        //sort every segment by arrival date
        for (List<SegmentDTO> list : segmentGroups) {
            list.sort(Comparator.comparing(SegmentDTO::getArrival));
        }
        return segmentGroups;
    }

    public List<List<SegmentDTO>> separateIntoGroups(List<SegmentDTO> segments) {
        List<List<SegmentDTO>> segmentGroups = new ArrayList<>();
        List<SegmentDTO> group = new ArrayList<>();
        String trainName = segments.get(0).getTrain().getId();
        for (SegmentDTO segment : segments) {
            if (!segment.getTrain().getId().equals(trainName)) {
                segmentGroups.add(group);
                group.clear();
                trainName = segment.getTrain().getId();
            }
            group.add(segment);
        }
        segmentGroups.add(group);
        return segmentGroups;
    }
}
