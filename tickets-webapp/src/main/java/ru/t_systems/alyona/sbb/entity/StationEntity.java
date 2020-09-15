package ru.t_systems.alyona.sbb.entity;

import lombok.*;
import ru.t_systems.alyona.sbb.converter.ZoneIdConverter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.ZoneId;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "station")
public class StationEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @Column(name = "id_zone")
    @Convert(converter = ZoneIdConverter.class)
    private ZoneId zoneId;

    @OneToMany(mappedBy = "stationFrom", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<SegmentTemplateEntity> segmentsTemplateFromStation;

    @OneToMany(mappedBy = "stationTo")
    @ToString.Exclude
    private List<SegmentTemplateEntity> segmentsTemplateToStation;

}
