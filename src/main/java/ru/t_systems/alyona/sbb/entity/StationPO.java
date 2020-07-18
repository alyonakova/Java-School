package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "station")
public class StationPO {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "name")
    private String name;

}
