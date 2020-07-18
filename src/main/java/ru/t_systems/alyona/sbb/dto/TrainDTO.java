package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

@Data
public class TrainDTO {

    private String id;
    private int seatsCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainDTO that = (TrainDTO) o;
        return id.equals(that.id) &&
                (seatsCount == that.seatsCount);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (id.hashCode() + seatsCount);
    }
}
