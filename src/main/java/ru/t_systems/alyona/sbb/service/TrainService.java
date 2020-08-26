package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.*;

import java.util.List;

@Service
public interface TrainService {

    List<TrainDTO> getAllTrains();
    OperationResultDTO createTrain(CreateTrainRequestDTO request);
    List<TrainDepartureDTO> getDeparturesByTrain(String trainNumber);
    List<SegmentTemplateDTO> getSegmentsByTrainNumber(String trainNumber);
}
