package ru.t_systems.alyona.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionSearchQueryDTO {

    @NotNull(message = "Departure station name can't be empty")
    String departureStationName;

    @NotNull(message = "Arrival station name can't be empty")
    String arrivalStationName;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Departure date can't be null")
    LocalDateTime departureTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Arrival date can't be empty")
    LocalDateTime arrivalTime;
}
