package ru.t_systems.alyona.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.ZoneId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationDTO {

    private BigInteger id;

    @NotNull(message = "Name can't be empty")
    @Size(min = 3, max = 50, message = "Min name length is 3, max is 50")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "Latin letters, hyphens, spaces are allowed")
    private String name;

    @NotNull(message = "Zone id can't be empty")
    private ZoneId zoneId;

}
