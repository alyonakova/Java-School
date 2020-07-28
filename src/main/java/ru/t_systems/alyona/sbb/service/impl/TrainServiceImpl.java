package ru.t_systems.alyona.sbb.service.impl;

import lombok.RequiredArgsConstructor;
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

    @Override
    public List<TrainDTO> getAllTrains() {
        return trainConverter.trainListToDTOList(trainRepository.getAll());
    }
}
