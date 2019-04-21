package com.graph.core.strategy;

import com.graph.core.dataaccess.entity.edge.Edge;
import com.graph.core.dataaccess.entity.edge.EdgeDirection;
import com.graph.core.dataaccess.entity.graph.AbstractGraph;
import com.graph.core.dataaccess.entity.path.SimplePath;
import com.graph.core.dataaccess.entity.vertex.Vertex;
import lombok.NonNull;

import java.util.*;

/**
 *  {@link ForceEnumerationStrategy} is the force enumeration strategy implementation of the {@link PathfinderStrategy}.
 *   Strategy recursively looking for end vertex starts from start vertex
 *   and going through all the associated vertices.
 */
public class ForceEnumerationStrategy implements PathfinderStrategy {
    private static final String VERTEXES_DOES_NOT_EXISTS_MASSAGE = "Vertexes doesn't exists";
    private Set<Vertex> visitedVertexes;
    private SimplePath path;

    /**
     * Find path from the {@code startVertex} to {@code endVertex} in graph {@code graph}.
     *
     * @param graph  in which the path is looking for
     * @param startVertex  the vertex where the path starts
     * @param endVertex  the vertex where the path ends
     * @throws IllegalArgumentException if at least one vertices doesn't exists
     * @return {@code SimplePath} path between two vertexes
     */
    public SimplePath findPath(@NonNull final AbstractGraph graph, @NonNull final Vertex startVertex, @NonNull final Vertex endVertex) {
        visitedVertexes = new HashSet<>();
        path = new SimplePath(new ArrayList<>(), new ArrayList<>());
        if (!graph.isGraphContainVertices(startVertex, endVertex)) {
            throw new IllegalArgumentException(VERTEXES_DOES_NOT_EXISTS_MASSAGE);
        }
        forceEnumeration(graph, startVertex, endVertex);
        path.reversePath();
        return path;
    }

    private boolean forceEnumeration(final AbstractGraph graph, final Vertex startVertex, final Vertex endVertex) {
        //Check current Vertex
        if (visitedVertexes.contains(startVertex)) {
            return false;
        } else {
            visitedVertexes.add(startVertex);
        }
        // starting path creation if the current vertex has edge with the end vertex
        if (startVertex.getEdges().containsKey(endVertex.getId()) && isEdgeDirectionFromVertex(startVertex.getEdges().get(endVertex.getId()))) {
            addVertexToPath(endVertex, endVertex.getEdges().get(startVertex.getId()));
            addVertexToPath(startVertex, startVertex.getEdges().get(endVertex.getId()));
            return true;
        } else {
            // If the current vertex doesn't have edge with the end one
            // looking for a edge in connected vertices
            for (Map.Entry<UUID, Edge> connection: startVertex.getEdges().entrySet()) {
                Vertex vertex = graph.getVertexById(connection.getKey());
                //If the edge isn't REVERSE - recursively looking for end vertex in connected vertices
                if (isEdgeDirectionFromVertex(connection.getValue())) {
                    //If path was found - add current vertex to path end moving to the top one
                    if (forceEnumeration(graph, vertex, endVertex)) {
                        addVertexToPath(startVertex, startVertex.getEdges().get(vertex.getId()));
                        return true;
                    }
                }
            }
        }
        //If path wasn't found - return false
        return false;
    }

    private void addVertexToPath(final Vertex vertex, final Edge edge) {
        path.getEdges().add(edge);
        path.getVertices().add(vertex);
    }

    private boolean isEdgeDirectionFromVertex(final Edge edge) {
        return edge.getDirection().equals(EdgeDirection.ABOTH) || edge.getDirection().equals(EdgeDirection.DIRECT);
    }
}
