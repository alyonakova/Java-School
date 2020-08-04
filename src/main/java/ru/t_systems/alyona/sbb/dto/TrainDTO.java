package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class TrainDTO {

    private String id;

    @Min(value = 5, message = "Min train capacity is 5")
    @NotNull(message = "Train capacity can't be empty")
    private int seatsCount;

}
