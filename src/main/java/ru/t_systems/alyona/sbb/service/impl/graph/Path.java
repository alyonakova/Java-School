package ru.t_systems.alyona.sbb.service.impl.graph;

import ru.t_systems.alyona.sbb.service.impl.graph.support.SimplePath;

import java.util.Collections;
import java.util.List;

public interface Path<
        V extends Vertex<V, E>,
        E extends Edge<V, E>> {

    static <E extends Edge<V, E>, V extends Vertex<V, E>> Path<V, E> emptyFrom(V source) {
        return new SimplePath<>(source, Collections.emptyList());
    }

    V getSource();

    List<V> getVertices();

    List<E> getEdges();

    V getTarget();

    boolean containsVertex(V linkedVertex);

    boolean containsEdge(E linkedEdge);

    Path<V, E> plus(E edge);

    boolean isEmpty();

    E getLastEdge();
}
