package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t_systems.alyona.sbb.converter.TrainDepartureConverter;
import ru.t_systems.alyona.sbb.entity.SegmentTemplateEntity;
import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;
import ru.t_systems.alyona.sbb.entity.TrainEntity;
import ru.t_systems.alyona.sbb.repository.StationRepository;
import ru.t_systems.alyona.sbb.repository.TrainRepository;
import ru.t_systems.alyona.sbb.service.TimetableService;
import ru.t_systems.alyona.sbb.timetable.TimetableDTO;
import ru.t_systems.alyona.sbb.timetable.TimetableSegmentDTO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TimetableServiceImpl implements TimetableService {

    private final TrainRepository trainRepository;
    private final TrainDepartureConverter departureConverter;
    private final StationRepository stationRepository;

    @Override
    @Transactional(readOnly = true)
    public TimetableDTO getTimetableByStationNameAndDate(String stationName, LocalDate date) {

        StationEntity station = stationRepository.getByName(stationName);

        List<TimetableSegmentDTO> segments = new ArrayList<>();

        for (TrainEntity train : trainRepository.getAll()) {
            for (TrainDepartureEntity departure : train.getDepartures()) {
                for (SegmentTemplateEntity segmentTemplate : train.getSegmentTemplates()) {

                    ZonedDateTime departureTime = departure.getDepartureTime()
                            .plus(segmentTemplate.getOffsetFromTrainDeparture(), ChronoUnit.MINUTES)
                            .atZone(station.getZoneId());
                    ZonedDateTime arrivalTime = departure.getDepartureTime()
                            .plus(segmentTemplate.getOffsetFromTrainDeparture(), ChronoUnit.MINUTES)
                            .plus(segmentTemplate.getTravelDuration(), ChronoUnit.MINUTES)
                            .atZone(station.getZoneId());

                    String segmentDepartureStation = segmentTemplate.getStationFrom().getName();
                    String segmentArrivalStation = segmentTemplate.getStationTo().getName();

                    if (segmentDepartureStation.equals(stationName) &&
                            areDatesInTheSameDay(departureTime, date)
                            ||
                            segmentArrivalStation.equals(stationName) &&
                                    areDatesInTheSameDay(arrivalTime, date)) {

                        segments.add(TimetableSegmentDTO.builder()
                                .trainNumber(train.getId())
                                .departureStation(segmentDepartureStation)
                                .departureDate(departureTime)
                                .arrivalStation(segmentArrivalStation)
                                .arrivalDate(arrivalTime)
                                .status(departureConverter.toDTO(departure).getStatus())
                                .trainDepartureDate(departure.getDepartureTime().atZone(ZoneId.of("UTC")))
                                .build());
                    }
                }
            }
        }

        return TimetableDTO.builder()
                .segments(segments)
                .stationName(stationName)
                .build();
    }

    private boolean areDatesInTheSameDay(ZonedDateTime dateTime, LocalDate day) {
        return dateTime.toLocalDate().equals(day);
    }

}
