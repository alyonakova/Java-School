package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.TrainDTO;

import java.util.List;

@Service
public interface TrainService {

    List<TrainDTO> getAllTrains();
}
