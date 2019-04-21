package com.graph.core.dataaccess.entity.graph;

import com.graph.core.dataaccess.entity.AbstractEntity;
import com.graph.core.dataaccess.entity.edge.Edge;
import com.graph.core.dataaccess.entity.path.SimplePath;
import com.graph.core.dataaccess.entity.vertex.Vertex;
import com.graph.core.strategy.PathfinderStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static java.util.Arrays.asList;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractGraph extends AbstractEntity {
    private Set<Vertex> vertexes;
    private Set<UUID> edgesIds;

    public abstract void addVertex(@NonNull final Vertex vertex);

    public abstract Vertex getVertexById(@NonNull final UUID id);

    public abstract void addEdge(@NonNull final Vertex vertexFrom, @NonNull final Vertex vertexTo, @NonNull final Edge edge);

    public SimplePath findPath(@NonNull final Vertex vertexFrom, @NonNull final Vertex vertexTo, @NonNull final PathfinderStrategy strategy) {
        return strategy.findPath(this, vertexFrom, vertexTo);
    }

    /**
     * Check if the graph contain collection of the vertices
     */
    final public boolean isGraphContainVertices(@NonNull final Vertex... vertices) {
        return vertexes.containsAll(asList(vertices));
    }

    /**
     * Check if the graph already contain edge.
     *
     * @return {@code True} if vertexes doesn't contain edge between each other and if edge doesn't exists
     * in other connections.
     */
    final boolean isEdgeAlreadyExists(@NonNull final Vertex vertexFrom, @NonNull final Vertex vertexTo, @NonNull final Edge edge) {
        final Optional<Edge> edgVertexFrom = Optional.ofNullable(vertexFrom
                .getEdges()
                .get(vertexTo.getId()));
        final Optional<Edge> edgVertexTo = Optional.ofNullable(vertexTo
                .getEdges()
                .get(vertexFrom.getId()));
        return edgVertexFrom.isPresent() || edgVertexTo.isPresent() || edgesIds.contains(edge.getId());
    }

}
