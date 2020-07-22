package ru.t_systems.alyona.sbb.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class RouteDTO {

    private BigInteger id;
    private StationDTO from;
    private StationDTO to;
    private List<SegmentDTO> segments;

}
