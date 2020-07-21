package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;

import java.time.Instant;
import java.util.List;

@Service
public interface SegmentService {

    List<List<SegmentDTO>> getSegmentGroupsByStationsAndDates(String from, String to, Instant departure, Instant arrival);
}
