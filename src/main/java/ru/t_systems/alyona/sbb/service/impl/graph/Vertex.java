package ru.t_systems.alyona.sbb.service.impl.graph;

import java.util.Collection;

public interface Vertex<
        V extends Vertex<V, E>,
        E extends Edge<V, E>> {

    Collection<E> getAllOutgoingEdges();
}
