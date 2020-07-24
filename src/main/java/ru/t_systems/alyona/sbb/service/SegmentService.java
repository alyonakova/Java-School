package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface SegmentService {

    List<List<SegmentDTO>> getSegmentGroupsByStationsAndDates(String departureStationName, String arrivalStationName,
                                                              LocalDateTime departureTime, LocalDateTime arrivalTime);
    List<SegmentDTO> getAllSegments();
}
