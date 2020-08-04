package ru.t_systems.alyona.sbb.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class FindTrainFormDTO {

    @NotNull(message = "First station name can't be empty")
    String firstStationName;

    @NotNull(message = "Last station name can't be empty")
    String secondStationName;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "First date can't be null")
    LocalDateTime firstDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Second date can't be empty")
    LocalDateTime secondDate;
}
