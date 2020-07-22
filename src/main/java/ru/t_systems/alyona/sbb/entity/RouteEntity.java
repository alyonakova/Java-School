package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "route")
public class RouteEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "id_station_from")
    private StationEntity from;

    @ManyToOne
    @JoinColumn(name = "id_station_to")
    private StationEntity to;

    @ManyToMany
    @JoinTable(name = "route_segment",
            joinColumns = @JoinColumn(name = "id_route"),
            inverseJoinColumns = @JoinColumn(name = "id_segment"))
    private List<SegmentEntity> segments;
}
