package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.converter.SegmentConverter;
import ru.t_systems.alyona.sbb.dto.RouteDTO;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;
import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.repository.SegmentRepository;
import ru.t_systems.alyona.sbb.service.SegmentService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SegmentServiceImpl implements SegmentService {

    private final StationServiceImpl stationService;
    private final RouteServiceImpl routeService;
    private final SegmentRepository segmentRepository;
    private final SegmentConverter segmentConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(SegmentServiceImpl.class);

    @Override
    public List<List<SegmentDTO>> getSegmentGroupsByStationsAndDates(String departureStationName, String arrivalStationName,
                                                                     LocalDateTime departureTime, LocalDateTime arrivalTime)
    {
        StationEntity departureStation = stationService.getEntityByName(departureStationName);
        StationEntity arrivalStation = stationService.getEntityByName(arrivalStationName);
        RouteDTO route = routeService.getByStations(departureStation, arrivalStation);
        return getSortedSegmentGroups(
                getSegmentsByRouteAndDatesAndTickets(route,
                        departureTime.atZone(departureStation.getZoneId()).toInstant(),
                        arrivalTime.atZone(arrivalStation.getZoneId()).toInstant())
        );
    }

    @Override
    public List<SegmentDTO> getAllSegments() {
        return segmentConverter.segmentListToDTOList(segmentRepository.getAll());
    }

    private List<SegmentDTO> getSegmentsByRouteAndDatesAndTickets(RouteDTO route, Instant departure, Instant arrival) {
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

    private List<List<SegmentDTO>> getSortedSegmentGroups(List<SegmentDTO> segments) {
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

    private List<List<SegmentDTO>> separateIntoGroups(List<SegmentDTO> segments) {
        List<List<SegmentDTO>> segmentGroups = new ArrayList<>();
        List<SegmentDTO> group = new ArrayList<>();
        String trainName = segments.get(0).getTrain().getId();
        for (SegmentDTO segment : segments) {
            if (!segment.getTrain().getId().equals(trainName)) {
                List<SegmentDTO> oldGroup = new ArrayList<>(group);
                segmentGroups.add(oldGroup);
                group.clear();
                trainName = segment.getTrain().getId();
            }
            group.add(segment);
        }
        segmentGroups.add(group);
        return segmentGroups;
    }
}
