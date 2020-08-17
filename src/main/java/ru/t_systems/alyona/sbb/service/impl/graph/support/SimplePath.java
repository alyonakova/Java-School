package ru.t_systems.alyona.sbb.service.impl.graph.support;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.t_systems.alyona.sbb.service.impl.graph.Edge;
import ru.t_systems.alyona.sbb.service.impl.graph.Path;
import ru.t_systems.alyona.sbb.service.impl.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public class SimplePath<
        V extends Vertex<V, E>,
        E extends Edge<V, E>> implements Path<V, E> {

    private final V source;
    private final List<E> edges;

    public SimplePath(V source, List<E> edges) {
        this.source = source;
        this.edges = edges;
    }

    @Override
    public V getSource() {
        return source;
    }

    @Override
    public List<E> getEdges() {
        return edges;
    }

    @Override
    public List<V> getVertices() {
        ArrayList<V> vertices = new ArrayList<>();
        vertices.add(source);
        for (E edge : edges) {
            vertices.add(edge.getTarget());
        }
        return vertices;
    }

    @Override
    public V getTarget() {
        return getLastVertex();
    }

    @Override
    public boolean containsVertex(V vertex) {
        return source.equals(vertex) || edges.stream().anyMatch(e -> e.getTarget().equals(vertex));
    }

    @Override
    public boolean containsEdge(E edge) {
        return edges.contains(edge);
    }

    @Override
    public boolean isEmpty() {
        return edges.isEmpty();
    }

    @Override
    public Path<V, E> plus(E edge) {
        if (!getLastVertex().equals(edge.getSource())) {
            throw new IllegalArgumentException("Cannot add a non-adjacent edge to a path");
        }
        List<E> newEdges = new ArrayList<>(edges);
        newEdges.add(edge);
        return new SimplePath<>(source, newEdges);
    }

    private V getLastVertex() {
        if (edges.isEmpty()) {
            return source;
        } else {
            return getLastEdge().getTarget();
        }
    }

    @Override
    public E getLastEdge() {
        return edges.get(edges.size() - 1);
    }
}
