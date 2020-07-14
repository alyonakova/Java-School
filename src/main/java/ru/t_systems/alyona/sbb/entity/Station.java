package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    @Id
    @GeneratedValue
    private BigInteger id;

    private String name;

    @OneToMany(mappedBy = "station")
    private Collection<Timetable> timetables;

    @ManyToMany
    private Collection<Train> trains;

}
