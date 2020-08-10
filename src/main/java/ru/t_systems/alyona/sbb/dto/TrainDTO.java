package ru.t_systems.alyona.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDTO {

    private String id;

    @Min(value = 5, message = "Min train capacity is 5")
    @NotNull(message = "Train capacity can't be empty")
    private int seatsCount;

}
