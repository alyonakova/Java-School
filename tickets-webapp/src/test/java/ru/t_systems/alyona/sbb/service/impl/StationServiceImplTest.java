package ru.t_systems.alyona.sbb.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.t_systems.alyona.sbb.converter.StationConverter;
import ru.t_systems.alyona.sbb.dto.OperationResultDTO;
import ru.t_systems.alyona.sbb.dto.StationDTO;
import ru.t_systems.alyona.sbb.entity.StationEntity;
import ru.t_systems.alyona.sbb.repository.StationRepository;

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

    @Test
    void returnsSuccessfulOperation_ifStationCreated() {
        stationDTO = mock(StationDTO.class);
        given(stationRepository.getByName(stationDTO.getName())).willReturn(null);
        stationEntity = mock(StationEntity.class);
        given(stationConverter.toEntity(stationDTO)).willReturn(stationEntity);

        OperationResultDTO result = stationService.createStation(stationDTO);

        assertThat(result.isSuccessful()).isTrue();
    }

    @Test
    void returnsErrorOperation_ifStationNameExists() {
        stationDTO = mock(StationDTO.class);
        stationEntity = mock(StationEntity.class);
        given(stationRepository.getByName(stationDTO.getName())).willReturn(stationEntity);

        OperationResultDTO result = stationService.createStation(stationDTO);

        assertThat(result.isSuccessful()).isFalse();
    }

}