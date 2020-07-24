package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t_systems.alyona.sbb.converter.ZoneIdConverter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.ZoneId;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "station")
public class StationEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "from")
    private List<RouteEntity> routesFrom;

    @OneToMany(mappedBy = "to")
    private List<RouteEntity> routesTo;

    @OneToMany(mappedBy = "from")
    private List<SegmentEntity> segmentsFromStation;

    @OneToMany(mappedBy = "to")
    private List<SegmentEntity> segmentsToStation;

    @Column(name = "id_zone")
    @Convert(converter = ZoneIdConverter.class)
    private ZoneId zoneId;

}
