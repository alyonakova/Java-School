package ru.t_systems.alyona.sbb.service;

import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.dto.*;

import java.util.List;

@Service
public interface TrainService {

    List<TrainDTO> getAllTrains();

    OperationResultDTO createTrain(CreateTrainRequestDTO request);

    List<TrainDepartureDTO> getDeparturesByTrain(TrainDTO train);

    List<SegmentTemplateDTO> getSegmentsByTrainNumber(TrainDTO train);

    TrainDTO getById(String trainNumber);

    boolean isTrainCancelled(List<TrainDepartureDTO> trainDepartures);

    OperationResultDTO cancelTrain(TrainDTO train);

    OperationResultDTO restoreTrain(TrainDTO train);

    OperationResultDTO delayTrain(TrainDTO train, int delayInMinutes);

    TrainDepartureDTO getTrainDeparture(String trainNumber, String departureTime);

    OperationResultDTO cancelTrainDeparture(TrainDepartureDTO trainDeparture);

    OperationResultDTO restoreTrainDeparture(TrainDepartureDTO trainDeparture);
}
