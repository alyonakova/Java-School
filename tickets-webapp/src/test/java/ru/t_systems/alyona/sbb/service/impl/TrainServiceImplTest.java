package ru.t_systems.alyona.sbb.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.t_systems.alyona.sbb.converter.TrainConverter;
import ru.t_systems.alyona.sbb.converter.TrainDepartureConverter;
import ru.t_systems.alyona.sbb.dto.*;
import ru.t_systems.alyona.sbb.entity.SegmentTemplateEntity;
import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;
import ru.t_systems.alyona.sbb.entity.TrainEntity;
import ru.t_systems.alyona.sbb.repository.SegmentTemplateRepository;
import ru.t_systems.alyona.sbb.repository.TrainDepartureRepository;
import ru.t_systems.alyona.sbb.repository.TrainRepository;

import java.time.Instant;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TrainServiceImplTest {

    @InjectMocks
    TrainServiceImpl trainService;

    @Mock
    TrainRepository trainRepository;

    @Mock
    TrainConverter trainConverter;

    @Mock
    TrainDepartureConverter trainDepartureConverter;

    @Mock
    TrainDepartureRepository departureRepository;

    @Mock
    SegmentTemplateRepository segmentTemplateRepository;

    static final String TRAIN_ID = "5s";
    static final int DELAY = 10;

    TrainDTO trainDTO;
    TrainEntity trainEntity;

    @Nested
    class getById {

        @Test
        void returnsNull_ifUserNotFound() {
            given(trainRepository.getById(TRAIN_ID)).willReturn(null);

            trainDTO = trainService.getById(TRAIN_ID);

            assertThat(trainDTO).isNull();
        }

        @Test
        void returnsTrain_ifTrainExistsInRepository() {
            trainEntity = mock(TrainEntity.class);
            given(trainRepository.getById(TRAIN_ID)).willReturn(trainEntity);

            trainDTO = mock(TrainDTO.class);
            given(trainConverter.toDTO(trainEntity)).willReturn(trainDTO);

            TrainDTO train = trainService.getById(TRAIN_ID);

            assertThat(train).isEqualTo(trainDTO);
        }
    }

    @Nested
    class cancelTrain {

        @Test
        @Disabled
        //FIXME messages sender was added
        void returnsSuccessfulOperation_ifTrainCancelled() {
            trainDTO = mock(TrainDTO.class);
            trainEntity = mock(TrainEntity.class);
            given(trainConverter.toEntity(trainDTO)).willReturn(trainEntity);

            OperationResultDTO result = trainService.cancelTrain(trainDTO);

            assertThat(result.isSuccessful()).isTrue();

        }
    }

    @Nested
    class restoreTrain {

        @Test
        @Disabled
        //FIXME messages sender was added
        void returnsSuccessfulOperation_ifTrainRestored() {
            trainDTO = mock(TrainDTO.class);
            trainEntity = mock(TrainEntity.class);
            given(trainConverter.toEntity(trainDTO)).willReturn(trainEntity);

            OperationResultDTO result = trainService.restoreTrain(trainDTO);

            assertThat(result.isSuccessful()).isTrue();
        }
    }

    @Nested
    class getTrainDeparture {

        static final String DEPARTURE_TIME = "2020-11-13T10:00:00.00Z";

        @Test
        void returnsTrainDeparture_ifItExistsInRepository() {

            trainEntity = mock(TrainEntity.class);
            given(trainRepository.getById(TRAIN_ID)).willReturn(trainEntity);

            Instant departureTimeDate = Instant.parse(DEPARTURE_TIME);
            var trainDepartureEntity = mock(TrainDepartureEntity.class);
            given(departureRepository.getTrainDeparture(trainEntity, departureTimeDate)).willReturn(trainDepartureEntity);
            var trainDepartureDTO = mock(TrainDepartureDTO.class);
            given(trainDepartureConverter.toDTO(trainDepartureEntity)).willReturn(trainDepartureDTO);

            TrainDepartureDTO trainDeparture = trainService.getTrainDeparture(TRAIN_ID, DEPARTURE_TIME);

            assertThat(trainDeparture).isEqualTo(trainDepartureDTO);

        }

        @Test
        void returnsNull_ifTrainIsNotExists() {
            trainEntity = null;
            given(trainRepository.getById(TRAIN_ID)).willReturn(trainEntity);
            Instant departureTimeDate = Instant.parse(DEPARTURE_TIME);
            given(departureRepository.getTrainDeparture(trainEntity, departureTimeDate)).willReturn(null);

            TrainDepartureDTO trainDeparture = trainService.getTrainDeparture(TRAIN_ID, DEPARTURE_TIME);

            assertThat(trainDeparture).isNull();
        }
    }

    @Nested
    class getDeparturesByTrain {

        @BeforeEach
        void initGetDeparturesByTrain() {
            trainDTO = mock(TrainDTO.class);
            trainEntity = mock(TrainEntity.class);
            given(trainConverter.toEntity(trainDTO)).willReturn(trainEntity);
        }

        @Test
        void returnsTrainDepartureList_ifTrainHasDepartures() {
            var trainDepartureEntity = TrainDepartureEntity.builder().train(trainEntity).build();
            given(departureRepository.getDeparturesByTrain(trainEntity)).willReturn(List.of(trainDepartureEntity));
            var trainDepartureDTO = mock(TrainDepartureDTO.class);
            given(trainDepartureConverter.toDTOList(List.of(trainDepartureEntity))).willReturn(List.of(trainDepartureDTO));

            List<TrainDepartureDTO> result = trainService.getDeparturesByTrain(trainDTO);

            assertThat(result).isEqualTo(List.of(trainDepartureDTO));
        }

        @Test
        void returnsEmptyList_ifTrainHasNoDepartures() {
            given(departureRepository.getDeparturesByTrain(trainEntity)).willReturn(List.of());
            given(trainDepartureConverter.toDTOList(List.of())).willReturn(List.of());

            List<TrainDepartureDTO> result = trainService.getDeparturesByTrain(trainDTO);

            assertThat(result).isEmpty();
        }
    }

    @Nested
    class getSegmentsByTrain {

        @BeforeEach
        void initGetSegmentsByTrain() {
            trainDTO = mock(TrainDTO.class);
            trainEntity = mock(TrainEntity.class);
            given(trainConverter.toEntity(trainDTO)).willReturn(trainEntity);
        }

        @Test
        void returnsSegments_ifTrainHasThem() {
            SegmentTemplateEntity segment = SegmentTemplateEntity.builder()
                    .indexWithinTrainRoute(0)
                    .stationFrom(StationEntity.builder().name("Station1").build())
                    .stationTo(StationEntity.builder().name("Station2").build())
                    .build();
            given(segmentTemplateRepository.getByTrain(trainEntity)).willReturn(List.of(segment));

            List<SegmentTemplateDTO> result = trainService.getSegmentsByTrain(trainDTO);

            assertThat(result).isNotEmpty();
        }

        @Test
        void returnsEmptyList_IfTrainHasNoSegments() {
            given(segmentTemplateRepository.getByTrain(trainEntity)).willReturn(List.of());

            List<SegmentTemplateDTO> result = trainService.getSegmentsByTrain(trainDTO);

            assertThat(result).isEmpty();
        }
    }

    @Nested
    class isTrainCancelled {

        TrainDepartureDTO departure1;

        @BeforeEach
        void initIsTrainCancelled() {
            departure1 = TrainDepartureDTO.builder()
                    .cancelled(true)
                    .build();
        }

        @Test
        void returnsTrue_ifAllDeparturesCancelled() {
            var departure2 = TrainDepartureDTO.builder()
                    .cancelled(true)
                    .build();
            List<TrainDepartureDTO> departures = List.of(departure1, departure2);

            boolean isCancelled = trainService.isTrainCancelled(departures);

            assertThat(isCancelled).isTrue();
        }

        @Test
        void returnsFalse_ifSomeDeparturesNotCancelled() {
            var departure2 = TrainDepartureDTO.builder()
                    .cancelled(false)
                    .build();
            List<TrainDepartureDTO> departures = List.of(departure1, departure2);

            boolean isCancelled = trainService.isTrainCancelled(departures);

            assertThat(isCancelled).isFalse();
        }
    }

    @Nested
    class delayTrain {

        @Test
        @Disabled
        //FIXME messages sender was added
        void returnsSuccessfulOperation_ifTrainDelayed() {
            var trainDTO = mock(TrainDTO.class);
            var trainEntity = mock(TrainEntity.class);
            given(trainConverter.toEntity(trainDTO)).willReturn(trainEntity);

            OperationResultDTO result = trainService.delayTrain(trainDTO, DELAY);

            assertThat(result.isSuccessful()).isTrue();
        }
    }

    @Nested
    class cancelTrainDeparture {

        @Test
        @Disabled
        //FIXME messages sender was added
        void returnsSuccessfulOperation_ifDepartureCancelled() {
            var trainDepartureDTO = mock(TrainDepartureDTO.class);
            var trainDepartureEntity = mock(TrainDepartureEntity.class);
            given(trainDepartureConverter.toEntity(trainDepartureDTO)).willReturn(trainDepartureEntity);

            OperationResultDTO result = trainService.cancelTrainDeparture(trainDepartureDTO);

            assertThat(result.isSuccessful()).isTrue();
        }
    }

    @Nested
    class restoreTrainDeparture {

        @Test
        @Disabled
        //FIXME messages sender was added
        void returnsSuccessfulOperation_ifDepartureRestored() {
            var trainDepartureDTO = mock(TrainDepartureDTO.class);
            var trainDepartureEntity = mock(TrainDepartureEntity.class);
            given(trainDepartureConverter.toEntity(trainDepartureDTO)).willReturn(trainDepartureEntity);

            OperationResultDTO result = trainService.restoreTrainDeparture(trainDepartureDTO);

            assertThat(result.isSuccessful()).isTrue();
        }
    }

    @Nested
    class delayTrainDeparture {

        @Test
        @Disabled
        //FIXME messages sender was added
        void returnsSuccessfulOperation_ifDepartureDelayed() {
            var trainDepartureDTO = mock(TrainDepartureDTO.class);
            var trainDepartureEntity = mock(TrainDepartureEntity.class);
            given(trainDepartureConverter.toEntity(trainDepartureDTO)).willReturn(trainDepartureEntity);

            OperationResultDTO result = trainService.delayTrainDeparture(trainDepartureDTO, DELAY);

            assertThat(result.isSuccessful()).isTrue();
        }
    }

    @Nested
    class createTrain {

        @Test
        void returnsErrorOperation_ifTrainNumberExists() {
            CreateTrainRequestDTO request = CreateTrainRequestDTO.builder()
                    .id(TRAIN_ID)
                    .build();
            var trainEntity = mock(TrainEntity.class);
            given(trainRepository.getById(request.getId())).willReturn(trainEntity);

            OperationResultDTO result = trainService.createTrain(request);

            assertThat(result.isSuccessful()).isFalse();
        }
    }

}