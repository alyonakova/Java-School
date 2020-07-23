package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "segment")
public class SegmentEntity {

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

    @ManyToOne
    @JoinColumn(name = "id_train")
    private TrainEntity train;

    @Column(name = "departure")
    private Instant departure;

    @Column(name = "arrival")
    private Instant arrival;

    @Column(name = "tickets_left")
    private int ticketsLeft;

    @Column(name = "price")
    private int price;

    @Column(name = "cancelled")
    private Boolean cancelled;

    @Column(name = "delayed")
    private Boolean delayed;

    @ManyToMany
    @JoinTable(name = "ticket_segment",
    joinColumns = @JoinColumn(name = "id_segment"),
    inverseJoinColumns = @JoinColumn(name = "id_ticket"))
    private List<TicketEntity> tickets;

    @ManyToMany
    @JoinTable(name = "route_segment",
            joinColumns = @JoinColumn(name = "id_segment"),
            inverseJoinColumns = @JoinColumn(name = "id_route"))
    private List<RouteEntity> routes;
}
