package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private BigInteger id;

    private String name;
    private String surname;
    private LocalDate birthday;
    private boolean employee;
    private String login;

    @OneToMany(mappedBy = "user")
    private Collection<Ticket> tickets;
}
