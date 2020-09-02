package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class DelayFormDTO {

    @Min(value = 0, message = "Delay can not be less than 0")
    private int delayInMinutes;
}
