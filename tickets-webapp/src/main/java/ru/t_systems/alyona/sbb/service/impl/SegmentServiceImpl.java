package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t_systems.alyona.sbb.converter.StationConverter;
import ru.t_systems.alyona.sbb.converter.TrainConverter;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;
import ru.t_systems.alyona.sbb.dto.TrainDTO;
import ru.t_systems.alyona.sbb.entity.SegmentTemplateEntity;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;
import ru.t_systems.alyona.sbb.entity.TrainEntity;
import ru.t_systems.alyona.sbb.repository.TrainRepository;
import ru.t_systems.alyona.sbb.service.SegmentService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class SegmentServiceImpl implements SegmentService {

    private final TrainRepository trainRepository;
    private final TrainConverter trainConverter;
    private final StationConverter stationConverter;

    @Override
    @Transactional
    public List<SegmentDTO> getAllSegments() {
        List<SegmentDTO> result = new ArrayList<>();
        try {
            for (TrainEntity train : trainRepository.getAll()) {
                TrainDTO trainDTO = trainConverter.toDTO(train);
                for (TrainDepartureEntity departure : train.getDepartures()) {
                    for (SegmentTemplateEntity segmentTemplate : train.getSegmentTemplates()) {
                        Instant departureTime = departure.getDepartureTime()
                                .plus(segmentTemplate.getOffsetFromTrainDeparture(), ChronoUnit.MINUTES);
                        Instant arrivalTime = departure.getDepartureTime()
                                .plus(segmentTemplate.getOffsetFromTrainDeparture(), ChronoUnit.MINUTES)
                                .plus(segmentTemplate.getTravelDuration(), ChronoUnit.MINUTES);
                        result.add(SegmentDTO.builder()
                                .train(trainDTO)
                                .from(stationConverter.toDTO(segmentTemplate.getStationFrom()))
                                .departureTime(departureTime)
                                .to(stationConverter.toDTO(segmentTemplate.getStationTo()))
                                .arrivalTime(arrivalTime)
                                .build());
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to get all existing segments", e);
        }
        return result;
    }
}
