package ru.t_systems.alyona.sbb.service.impl.graph.algo;

import ru.t_systems.alyona.sbb.service.impl.graph.Edge;
import ru.t_systems.alyona.sbb.service.impl.graph.Path;
import ru.t_systems.alyona.sbb.service.impl.graph.Vertex;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public class DepthFirstSearch {

    public <V extends Vertex<V, E>,
            E extends Edge<V, E>>
    Collection<Path<V, E>> findAllSimplePaths(V source, V target, PathEdgeFilter<V, E> pathEdgeFilter) {
        HashSet<Path<V, E>> discoveredPathsToSource = new HashSet<>();
        discoveredPathsToSource.add(Path.emptyFrom(source));
        return new FindAllSimplePaths().findAllSimplePaths(source, target, discoveredPathsToSource, pathEdgeFilter);
    }

    @FunctionalInterface
    public interface PathEdgeFilter<
            V extends Vertex<V, E>,
            E extends Edge<V, E>> {

        boolean mayAppend(E nextEdge, Path<V, E> currentPath);
    }

    private static class FindAllSimplePaths {

        public <V extends Vertex<V, E>,
                E extends Edge<V, E>>
        Collection<Path<V, E>> findAllSimplePaths(
                V source, V target,
                Collection<Path<V, E>> pathsToSource,
                PathEdgeFilter<V, E> pathEdgeFilter) {

            if (Objects.equals(source, target)) {
                return pathsToSource;
            }

            Collection<E> allOutgoingEdges = source.getAllOutgoingEdges();
            return allOutgoingEdges.stream()
                    .filter(nextEdge -> pathsToSource.stream().noneMatch(path -> path.containsVertex(nextEdge.getTarget())))
                    .flatMap(nextEdge -> {
                        V nextSource = nextEdge.getTarget();
                        Collection<Path<V, E>> pathsToNextSource = pathsToSource.stream()
                                .filter(path -> pathEdgeFilter.mayAppend(nextEdge, path))
                                .map(path -> path.plus(nextEdge))
                                .collect(Collectors.toList());
                        return findAllSimplePaths(nextSource, target, pathsToNextSource, pathEdgeFilter).stream();
                    })
                    .collect(Collectors.toList());
        }
    }
}
