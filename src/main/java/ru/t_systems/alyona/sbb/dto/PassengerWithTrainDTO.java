package ru.t_systems.alyona.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PassengerWithTrainDTO {

    private PassengerDTO passenger;
    private TrainDTO train;

}
