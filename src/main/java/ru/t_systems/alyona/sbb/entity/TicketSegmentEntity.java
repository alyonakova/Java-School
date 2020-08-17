package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.Instant;

@Entity
@Table(name = "ticket_segment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(TicketSegmentEntity.ID.class)
public class TicketSegmentEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_ticket", referencedColumnName = "id")
    TicketEntity ticket;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_segment_template", referencedColumnName = "id")
    SegmentTemplateEntity segmentTemplate;

    @Id
    @Column(name = "train_departure_time")
    Instant trainDepartureTime;

    @Column(name = "index_in_ticket")
    Integer indexInTicket;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ID implements Serializable {
        BigInteger ticket;
        BigInteger segmentTemplate;
        Instant trainDepartureTime;
    }
}
