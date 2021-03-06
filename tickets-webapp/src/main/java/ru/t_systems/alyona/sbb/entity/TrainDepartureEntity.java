package ru.t_systems.alyona.sbb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "train_departure")
@IdClass(TrainDepartureEntity.ID.class)
public class TrainDepartureEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_train", referencedColumnName = "id")
    private TrainEntity train;

    @Id
    @Column(name = "departure_time")
    private Instant departureTime;

    @Column(name = "cancelled")
    private boolean cancelled;

    @Column(name = "delay")
    private int delayInMinutes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ID implements Serializable {
        String train;
        Instant departureTime;
    }
}
