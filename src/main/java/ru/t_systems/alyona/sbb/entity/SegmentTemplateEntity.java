package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "segment_template")
public class SegmentTemplateEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "id_train")
    private TrainEntity train;

    @Column(name = "index_within_train_route")
    private int indexWithinTrainRoute;

    @JoinColumn(name = "station_from")
    @ManyToOne
    private StationEntity stationFrom;

    @JoinColumn(name = "station_to")
    @ManyToOne
    private StationEntity stationTo;

    @Column(name = "train_departure_offset")
    private int offsetFromTrainDeparture;

    @Column(name = "travel_duration")
    private int travelDuration;

    @Column(name = "stop_duration")
    private int stopDuration;

    @Column(name = "price")
    private int price;
}
