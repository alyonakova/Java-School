package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "train")
public class TrainEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "seats_count")
    private int seatsCount;

    @OneToMany(mappedBy = "train")
    private List<SegmentEntity> segments;


}
