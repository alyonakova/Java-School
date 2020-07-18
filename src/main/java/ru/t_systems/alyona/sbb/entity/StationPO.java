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
@Table(name = "station")
public class StationPO {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "from")
    private Collection<RoutePO> routesFrom;

    @OneToMany(mappedBy = "to")
    private Collection<RoutePO> routesTo;

    @OneToMany(mappedBy = "from")
    private Collection<SegmentPO> segmentsFromStation;

    @OneToMany(mappedBy = "to")
    private Collection<SegmentPO> segmentsToStation;

}
