package ru.t_systems.alyona.sbb.service.impl.graph;

public interface Edge<
        V extends Vertex<V, E>,
        E extends Edge<V, E>> {

    V getSource();
    V getTarget();
}
