package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class TicketPO {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "id_passenger")
    private PassengerPO passenger;

    @ManyToMany
    @JoinTable(name = "ticket_segment",
            joinColumns = @JoinColumn(name = "id_ticket"),
            inverseJoinColumns = @JoinColumn(name = "id_segment"))
    private List<SegmentPO> segments;

}
