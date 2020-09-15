package ru.t_systems.alyona.sbb.service.impl.route_graph.impl.db;

import lombok.Builder;
import ru.t_systems.alyona.sbb.converter.TrainConverterImpl;
import ru.t_systems.alyona.sbb.dto.SegmentDTO;
import ru.t_systems.alyona.sbb.entity.SegmentTemplateEntity;
import ru.t_systems.alyona.sbb.entity.TrainDepartureEntity;
import ru.t_systems.alyona.sbb.entity.TrainEntity;
import ru.t_systems.alyona.sbb.service.impl.route_graph.RouteGraph;
import ru.t_systems.alyona.sbb.service.impl.route_graph.SegmentEdge;
import ru.t_systems.alyona.sbb.service.impl.route_graph.StationNode;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Builder
public class DatabaseBackedSegmentEdgeImpl implements SegmentEdge {

    private final RouteGraph graph;
    private final StationNode sourceStationNode;
    private final SegmentTemplateEntity segmentTemplateEntity;
    private final TrainDepartureEntity trainDepartureEntity;

    @Override
    public StationNode getSource() {
        return sourceStationNode;
    }

    @Override
    public StationNode getTarget() {
        return graph.findStationNodeByName(segmentTemplateEntity.getStationTo().getName());
    }

    public ZonedDateTime getDepartureTime() {
        return trainDepartureEntity.getDepartureTime()
                .plus(segmentTemplateEntity.getOffsetFromTrainDeparture(), ChronoUnit.MINUTES)
                .atZone(segmentTemplateEntity.getStationFrom().getZoneId());
    }

    public ZonedDateTime getArrivalTime() {
        return trainDepartureEntity.getDepartureTime()
                .plus(segmentTemplateEntity.getOffsetFromTrainDeparture(), ChronoUnit.MINUTES)
                .plus(segmentTemplateEntity.getTravelDuration(), ChronoUnit.MINUTES)
                .atZone(segmentTemplateEntity.getStationTo().getZoneId());
    }

    @Override
    public int getPriceFranks() {
        return segmentTemplateEntity.getPrice();
    }

    @Override
    public int getAvailableTicketsCount() {
        int boughtTicketsCount = getBoughtTicketsCount();
        return getTrain().getSeatsCount() - boughtTicketsCount;
    }

    public int getBoughtTicketsCount() {
        return graph.countBoughtTickets(segmentTemplateEntity, trainDepartureEntity);
    }

    @Override
    public String getTrainNumber() {
        return getTrain().getId();
    }

    @Override
    public SegmentDTO toDTO() {
        return SegmentDTO.builder()
                .segmentTemplateId(segmentTemplateEntity.getId())
                .trainDepartureTime(trainDepartureEntity.getDepartureTime())
                .from(getSource().toDTO())
                .to(getTarget().toDTO())
                .train(getTrainConverter().toDTO(getTrain()))
                .departureTime(getDepartureTime().toInstant())
                .arrivalTime(getArrivalTime().toInstant())
                .ticketsLeft(getAvailableTicketsCount())
                .price(getPriceFranks())
                .cancelled(isCancelled())
                .delayedMinutes(delayedInMinutes())
                .build();
    }

    private boolean isCancelled() {
        // TODO: Implement cancellation of segments
        return false;
    }

    private int delayedInMinutes() {
        // TODO: Implement delaying of segments
        return 0;
    }

    private TrainEntity getTrain() {
        return segmentTemplateEntity.getTrain();
    }

    private TrainConverterImpl getTrainConverter() {
        return new TrainConverterImpl();
    }

    @Override
    public String toString() {
        return "Edge("+sourceStationNode.getName()+" -> "+segmentTemplateEntity.getStationTo().getName()+")";
    }
}
