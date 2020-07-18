package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "segment")
public class SegmentPO {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "id_station_from")
    private StationPO from;

    @ManyToOne
    @JoinColumn(name = "id_station_to")
    private StationPO to;

    @ManyToOne
    @JoinColumn(name = "id_train")
    private TrainPO train;

    @Column(name = "departure")
    private Calendar departure;

    @Column(name = "arrival")
    private Calendar arrival;

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
    private Collection<TicketPO> tickets;
}
