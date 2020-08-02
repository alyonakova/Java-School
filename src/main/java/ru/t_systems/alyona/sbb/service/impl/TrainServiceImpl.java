package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.t_systems.alyona.sbb.converter.TrainConverter;
import ru.t_systems.alyona.sbb.dto.TrainDTO;
import ru.t_systems.alyona.sbb.repository.TrainRepository;
import ru.t_systems.alyona.sbb.service.TrainService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TrainServiceImpl implements TrainService {

    private final TrainConverter trainConverter;
    private final TrainRepository trainRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainServiceImpl.class);

    @Override
    public List<TrainDTO> getAllTrains() {
        List<TrainDTO> result = null;
        try {
            result = trainConverter.trainListToDTOList(trainRepository.getAll());
        } catch (Exception e) {
            LOGGER.error("Failed to get all existing trains", e);
        }
        return result;
    }
}
