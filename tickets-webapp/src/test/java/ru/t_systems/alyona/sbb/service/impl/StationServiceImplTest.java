package ru.t_systems.alyona.sbb.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.t_systems.alyona.sbb.converter.StationConverter;
import ru.t_systems.alyona.sbb.dto.OperationResultDTO;
import ru.t_systems.alyona.sbb.dto.StationDTO;
import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.repository.StationRepository;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static com.google.common.truth.Truth.assertThat;

@ExtendWith(MockitoExtension.class)
class StationServiceImplTest {

    @InjectMocks
    StationServiceImpl stationService;

    @Mock
    StationConverter stationConverter;

    @Mock
    StationRepository stationRepository;

    StationDTO stationDTO;
    StationEntity stationEntity;

    @BeforeEach
    void init() {
        stationDTO = mock(StationDTO.class);
        stationEntity = mock(StationEntity.class);
    }

    @Nested
    class createStation {

        @Test
        void returnsSuccessfulOperation_ifStationCreated() {
            given(stationRepository.getByName(stationDTO.getName())).willReturn(null);
            given(stationConverter.toEntity(stationDTO)).willReturn(stationEntity);

            OperationResultDTO result = stationService.createStation(stationDTO);

            assertThat(result.isSuccessful()).isTrue();
        }

        @Test
        void returnsErrorOperation_ifStationNameExists() {
            given(stationRepository.getByName(stationDTO.getName())).willReturn(stationEntity);

            OperationResultDTO result = stationService.createStation(stationDTO);

            assertThat(result.isSuccessful()).isFalse();
        }
    }

    @Nested
    class getAllStations {

        @Test
        void returnsAllStations_ifTheyExist() {
            given(stationRepository.getAll()).willReturn(List.of(stationEntity));
            given(stationConverter.toDTOList(List.of(stationEntity))).willReturn(List.of(stationDTO));

            List<StationDTO> result = stationService.getAllStations();

            assertThat(result).isNotEmpty();
        }

        @Test
        void returnsEmptyList_ifNoStationsExist() {
            given(stationRepository.getAll()).willReturn(List.of());

            List<StationDTO> result = stationService.getAllStations();

            assertThat(result).isEmpty();
        }
    }
}