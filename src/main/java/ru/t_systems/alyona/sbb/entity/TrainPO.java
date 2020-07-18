package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "train")
public class TrainPO {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "seats_count")
    private int seatsCount;

    @OneToMany(mappedBy = "train")
    private Collection<SegmentPO> segments;


}
