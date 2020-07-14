package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timetable {

    @Id
    @GeneratedValue
    private BigInteger id;

    @ManyToOne
    private Train train;

    private boolean arrival;
    private LocalDate time;

    @ManyToOne
    private Station station;

}
