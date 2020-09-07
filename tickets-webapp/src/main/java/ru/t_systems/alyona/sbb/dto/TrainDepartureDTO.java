package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class TrainDepartureDTO {

    private TrainDTO train;

    private Instant departureTime;

    private boolean cancelled;

    private int delayInMinutes;

    public boolean isDelayed() {
        return this.delayInMinutes > 0;
    }

    public String getStatus() {
        if (cancelled) return "Cancelled";
        if (isDelayed()) return "Delayed for " + this.delayInMinutes + " min";
        return "On time";
    }
}
