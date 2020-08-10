package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface SegmentService {

    /**
     * 
     * @deprecated Use {@link ru.t_systems.alyona.sbb.service.impl.RouteSearchServiceImpl#}
     * @param departureStationName
     * @param arrivalStationName
     * @param departureTime
     * @param arrivalTime
     * @return
     */
    @Deprecated
    List<List<SegmentDTO>> getSegmentGroupsByStationsAndDates(String departureStationName, String arrivalStationName,
                                                              LocalDateTime departureTime, LocalDateTime arrivalTime);
    List<SegmentDTO> getAllSegments();
}
