package ru.t_systems.alyona.sbb.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
public class ChangeUserDataDTO {

    private BigInteger id;

    @Size(min = 3, max = 60, message = "Min login length is 3, max is 60")
    private String login;

    @Size(min = 4, max = 20, message = "Min password length is 4, max is 20")
    private String password;

    @Size(min = 3, max = 60, message = "Min login length is 3, max is 60")
    private String newLogin;

    @Size(min = 4, max = 20, message = "Min password length is 4, max is 20")
    private String newPassword;

    @Size(min = 2, max = 30, message = "Min name length is 2, max is 30")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "Latin letters, hyphens, spaces are allowed")
    private String newName;

    @Size(min = 2, max = 30, message = "Min surname length is 2, max is 30")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "Latin letters, hyphens, spaces are allowed")
    private String newSurname;

    @Past(message = "Birthday must be past date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate newBirthday;
}
