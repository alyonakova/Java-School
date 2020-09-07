package ru.t_systems.alyona.sbb.entity;

import lombok.*;

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
    @ToString.Exclude
    private List<SegmentTemplateEntity> segmentTemplates;

    @OneToMany(mappedBy = "train")
    @ToString.Exclude
    private List<TrainDepartureEntity> departures;
}
