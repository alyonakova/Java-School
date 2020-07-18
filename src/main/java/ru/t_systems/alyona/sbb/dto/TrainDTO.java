package ru.t_systems.alyona.sbb.dto;

import lombok.Data;
import ru.t_systems.alyona.sbb.entity.SegmentPO;

import java.util.Collection;

@Data
public class TrainDTO {

    private String id;
    private int seatsCount;
    private Collection<SegmentDTO> segments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainDTO that = (TrainDTO) o;
        return id.equals(that.id) &&
                (seatsCount == that.seatsCount) &&
                segments.equals(that.segments);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (id.hashCode() + seatsCount + segments.size());
    }
}
