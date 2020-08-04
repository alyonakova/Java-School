package ru.t_systems.alyona.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO implements Serializable {

    private BigInteger id;

    @NotNull(message = "Name can't be empty")
    @Size(min = 2, max = 30, message = "Min name length is 2, max is 30")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "Latin letters, hyphens, spaces are allowed")
    private String name;

    @NotNull(message = "Surname can't be empty")
    @Size(min = 2, max = 30, message = "Min surname length is 2, max is 30")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "Latin letters, hyphens, spaces are allowed")
    private String surname;

    @NotNull(message = "Birthday can't be empty")
    @Past(message = "Birthday must be past date")
    private LocalDate birthday;

}
