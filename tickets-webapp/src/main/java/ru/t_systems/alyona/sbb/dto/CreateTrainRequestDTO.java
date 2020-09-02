package ru.t_systems.alyona.sbb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateTrainRequestDTO {
    private String id;
    private int capacity;
    private SegmentTemplateDTO[] segments;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime[] departureTimes;
}
