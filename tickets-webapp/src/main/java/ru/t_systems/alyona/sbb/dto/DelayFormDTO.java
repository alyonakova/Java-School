package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Data
public class DelayFormDTO {

    @NotBlank(message = "Delay value is required.")
    @Digits(integer = 4, fraction = 0, message = "Delay must be an integer number between 0 and 1000.")
    @DecimalMin(value = "0", message = "Delay cannot be less than 0 minutes.")
    @DecimalMax(value = "1000", message = "Delay cannot be more than 1000 minutes.")
    private String delayInMinutes;
}
