package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;

import java.util.List;

@Service
public interface SegmentService {

    List<SegmentDTO> getAllSegments();
}
