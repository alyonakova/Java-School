package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t_systems.alyona.sbb.converter.TrainConverter;
import ru.t_systems.alyona.sbb.converter.TrainDepartureConverter;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.entity.SegmentTemplateEntity;
import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;
import ru.t_systems.alyona.sbb.entity.TrainEntity;
import ru.t_systems.alyona.sbb.repository.SegmentTemplateRepository;
import ru.t_systems.alyona.sbb.repository.StationRepository;
import ru.t_systems.alyona.sbb.repository.TrainDepartureRepository;
import ru.t_systems.alyona.sbb.repository.TrainRepository;
import ru.t_systems.alyona.sbb.service.TrainService;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class TrainServiceImpl implements TrainService {

    private final TrainConverter trainConverter;
    private final TrainRepository trainRepository;
    private final SegmentTemplateRepository segmentTemplateRepository;
    private final StationRepository stationRepository;
    private final TrainDepartureRepository departureRepository;
    private final TrainDepartureConverter trainDepartureConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainServiceImpl.class);

    @Override
    public List<TrainDTO> getAllTrains() {
        List<TrainDTO> result = null;
        try {
            result = trainConverter.toDTOList(trainRepository.getAll());
        } catch (Exception e) {
            LOGGER.error("Failed to get all existing trains", e);
        }
        return result;
    }

    @Transactional
    @Override
    public OperationResultDTO createTrain(CreateTrainRequestDTO request) {
        try {
            TrainEntity existingTrain = trainRepository.getById(request.getId());
            if (existingTrain != null) {
                return OperationResultDTO.error("Train with id " + request.getId() + " already exists.");
            }

            TrainDTO newTrain = new TrainDTO(request.getId(), request.getCapacity());
            TrainEntity trainEntity = trainRepository.create(trainConverter.toEntity(newTrain));

            List<SegmentTemplateEntity> segmentTemplateEntities =
                    buildSegmentTemplateEntities(request, trainEntity);
            segmentTemplateRepository.create(segmentTemplateEntities);

            ZoneId departureTimeZone = ZoneId.of("UTC");
            List<TrainDepartureEntity> trainDepartureEntities =
                    Arrays.stream(request.getDepartureTimes())
                            .map(time -> TrainDepartureEntity.builder()
                                    .train(trainEntity)
                                    .departureTime(time.atZone(departureTimeZone).toInstant())
                                    .cancelled(false)
                                    .delayInMinutes(0)
                                    .build())
                            .collect(toList());
            departureRepository.create(trainDepartureEntities);
        } catch (Exception e) {
            LOGGER.error("Failed to create a new train", e);
        }

        return OperationResultDTO.successful("Train successfully created.");
    }

    private List<SegmentTemplateEntity> buildSegmentTemplateEntities(CreateTrainRequestDTO request, TrainEntity trainEntity) {
        List<SegmentTemplateEntity> segmentTemplateEntities = new ArrayList<>();
        SegmentTemplateDTO[] segments = request.getSegments();
        int timeOffset = 0;
        for (int index = 0; index < segments.length; index++) {
            SegmentTemplateDTO dto = segments[index];
            StationEntity stationFrom = stationRepository.getByName(dto.getSourceStation());
            StationEntity stationTo = stationRepository.getByName(dto.getDestinationStation());
            SegmentTemplateEntity entity = SegmentTemplateEntity.builder()
                    .indexWithinTrainRoute(index)
                    .stationFrom(stationFrom)
                    .stationTo(stationTo)
                    .offsetFromTrainDeparture(timeOffset)
                    .travelDuration(dto.getTravelDurationMinutes())
                    .stopDuration(dto.getStopDurationMinutes())
                    .price(dto.getPriceFranks())
                    .train(trainEntity)
                    .build();
            segmentTemplateEntities.add(entity);
            timeOffset += dto.getTravelDurationMinutes() + dto.getStopDurationMinutes();
        }
        return segmentTemplateEntities;
    }

    @Override
    public List<TrainDepartureDTO> getDeparturesByTrain(TrainDTO train) {
        TrainEntity trainEntity = trainConverter.toEntity(train);
        return trainDepartureConverter
                .toDTOList(departureRepository.getDeparturesByTrain(trainEntity));
    }

    @Override
    public List<SegmentTemplateDTO> getSegmentsByTrainNumber(TrainDTO train) {
        List<SegmentTemplateDTO> result = new ArrayList<>();
        TrainEntity trainEntity = trainConverter.toEntity(train);
        List<SegmentTemplateEntity> segments = segmentTemplateRepository.getByTrain(trainEntity);
        for (SegmentTemplateEntity segment : segments) {
            result.add(SegmentTemplateDTO.builder()
                    .sourceStation(segment.getStationFrom().getName())
                    .destinationStation(segment.getStationTo().getName())
                    .indexWithinTrainRoute(segment.getIndexWithinTrainRoute())
                    .build());
        }
        result.sort(SegmentTemplateDTO.COMPARE_BY_ROUTE_INDEX);
        return result;
    }

    @Override
    public TrainDTO getById(String trainNumber) {
        return trainConverter.toDTO(trainRepository.getById(trainNumber));
    }

    @Override
    @Transactional
    public boolean isTrainCancelled(List<TrainDepartureDTO> trainDepartures) {
        return trainDepartures.stream().allMatch(TrainDepartureDTO::isCancelled);
    }

    @Override
    @Transactional
    public OperationResultDTO cancelTrain(TrainDTO train) {
        try {
            TrainEntity trainEntity = trainConverter.toEntity(train);
            departureRepository.cancelAllTrainDepartures(trainEntity);
        } catch (Exception e) {
            LOGGER.error("Failed to cancel the train", e);
        }
        return OperationResultDTO.successful("Train successfully cancelled");
    }

    @Override
    @Transactional
    public OperationResultDTO restoreTrain(TrainDTO train) {
        try {
            TrainEntity trainEntity = trainConverter.toEntity(train);
            departureRepository.restoreAllTrainDepartures(trainEntity);
        } catch (Exception e) {
            LOGGER.error("Failed to restore the train", e);
        }
        return OperationResultDTO.successful("Train successfully restored");
    }

    @Override
    @Transactional
    public OperationResultDTO delayTrain(TrainDTO train, int delayInMinutes) {
        try {
            TrainEntity trainEntity = trainConverter.toEntity(train);
            departureRepository.delayAllTrainDepartures(trainEntity, delayInMinutes);
        } catch (Exception e) {
            LOGGER.error("Failed to delay the train", e);
        }
        return OperationResultDTO.successful("Train successfully delayed");
    }

    @Override
    @Transactional
    public TrainDepartureDTO getTrainDeparture(String trainNumber, String departureTime) {
        TrainDepartureDTO trainDeparture = null;
        try {
            TrainEntity trainEntity = trainRepository.getById(trainNumber);
            Instant departureTimeDate = Instant.parse(departureTime);
            trainDeparture = trainDepartureConverter.toDTO(
                    departureRepository.getTrainDeparture(trainEntity, departureTimeDate)
            );
        } catch (Exception e) {
            LOGGER.error("Failed to get train departure", e);
        }
        return trainDeparture;
    }

}
