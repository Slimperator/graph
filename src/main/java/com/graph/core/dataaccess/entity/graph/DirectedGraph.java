package com.graph.core.dataaccess.entity.graph;

import com.graph.core.dataaccess.entity.edge.Edge;
import com.graph.core.dataaccess.entity.edge.EdgeDirection;
import com.graph.core.dataaccess.entity.vertex.Vertex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Arrays.asList;

/**
 *   {@link DirectedGraph} is the simple directed graph realisation.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DirectedGraph extends AbstractGraph {
    private static final String WRONG_DIRECTION_MASSAGE = "Vertex edge with itself can't be one-way-directed";
    private static final String VERTEXES_DOES_NOT_EXISTS_MASSAGE = "Vertexes doesn't exists";
    private static final String EDGE_ALREADY_EXISTS_MASSAGE = "Edge already exists";
    private static final String VERTEX_ALREADY_EXISTS_MASSAGE = "Vertexes already exist";

    public DirectedGraph(@NonNull Set<Vertex> vertexes) {
        super(vertexes, new HashSet<>());
    }

    /**
     * Add vertex to graph.
     *
     * @param vertex  the Vertex to add
     * @throws IllegalArgumentException if the graph already contain the Vertex
     */
    @Override
    public void addVertex(@NonNull final Vertex vertex) {
        if (isGraphContainVertices(vertex)) {
            throw new IllegalArgumentException(VERTEX_ALREADY_EXISTS_MASSAGE);
        }
        super.getVertexes().add(vertex);
    }

    /**
     * Add edge to graph.
     *
     * @param vertexFrom  the vertex from which the edge starts
     * @param vertexTo  the vertex where the edge ends
     * @throws IllegalArgumentException if the graph already contain the edge
     * @throws IllegalArgumentException if at least one vertices doesn't exists
     * @throws IllegalArgumentException if the edge to add has non-Aboth direction
     */
    @Override
    public void addEdge(@NonNull final Vertex vertexFrom, @NonNull final Vertex vertexTo, @NonNull final Edge edge) {
        if (!isGraphContainVertices(vertexFrom, vertexTo)) {
            throw new IllegalArgumentException(VERTEXES_DOES_NOT_EXISTS_MASSAGE);
        }
        if (isEdgeAlreadyExists(vertexFrom, vertexTo, edge)) {
            throw new IllegalArgumentException(EDGE_ALREADY_EXISTS_MASSAGE);
        }
        if (vertexFrom == vertexTo) {
            if (!edge.getDirection()
                    .equals(EdgeDirection.ABOTH)) {
                throw new IllegalArgumentException(WRONG_DIRECTION_MASSAGE);
            }
            super.getVertexes()
                    .remove(vertexFrom);
            vertexFrom.getEdges()
                    .put(vertexTo.getId(), edge);
            super.getVertexes()
                    .add(vertexFrom);
            super.getEdgesIds()
                    .add(edge.getId());
        } else {
            final Edge reversedEdge = edge.reverseEdge();
            super.getVertexes()
                    .removeAll(asList(vertexFrom, vertexTo));
            vertexFrom.getEdges()
                    .put(vertexTo.getId(), edge);
            vertexTo.getEdges()
                    .put(vertexFrom.getId(), reversedEdge);
            super.getVertexes()
                    .addAll(asList(vertexFrom, vertexTo));
            super.getEdgesIds()
                    .addAll(asList(edge.getId(), reversedEdge.getId()));
        }
    }

    @Override
    public Vertex getVertexById(@NonNull final UUID id) {
        return super.getVertexes()
                .stream()
                .filter(vertex -> vertex.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
