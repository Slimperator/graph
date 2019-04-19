package com.graph.core.dataaccess.entity.graph;

import com.graph.core.dataaccess.entity.edge.EdgeDirection;
import com.graph.core.dataaccess.entity.edge.NonWeightedEdge;
import com.graph.core.dataaccess.entity.vertex.SimpleVertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static java.util.Arrays.asList;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnderectedGraph extends AbstractGraph {
    private static final String WRONG_DIRECTION_MASSAGE = "Can't create directed edge in underected graph";
    private static final String VERTEXES_DOES_NOT_EXISTS_MASSAGE = "Vertexes doesn't exists";
    private static final String EDGE_ALREADY_EXISTS_MASSAGE = "Edge already exists";
    private static final String VERTEX_ALREADY_EXISTS_MASSAGE = "Vertexes already exist";

    public UnderectedGraph(Set<SimpleVertex> vertexes, Set<NonWeightedEdge> edges) {
        super(vertexes, edges);
    }

    @Override
    public void addVertex(@NonNull SimpleVertex vertex) {
        if (!isGraphContainVertexes(vertex)) {
            throw new IllegalArgumentException(VERTEX_ALREADY_EXISTS_MASSAGE);
        }
        super.getVertexes().add(vertex);
    }

    @Override
    public void addEdge(@NonNull SimpleVertex vertexFrom, @NonNull SimpleVertex vertexTo, @NonNull NonWeightedEdge edge) { //едже плохо. возможно фабрика + енам
        if (!edge.getDirection().equals(EdgeDirection.ABOTH)) {
            throw new IllegalArgumentException(WRONG_DIRECTION_MASSAGE);
        }
        if (!isGraphContainVertexes(vertexFrom, vertexTo)) {
            throw new IllegalArgumentException(VERTEXES_DOES_NOT_EXISTS_MASSAGE);
        }
        if (isEdgeAlreadyExists(vertexFrom, vertexTo, edge)) {
            throw new IllegalArgumentException(EDGE_ALREADY_EXISTS_MASSAGE);
        }
        //super.getVertexes().removeAll(asList(vertexFrom, vertexTo)); //возможно
        vertexFrom.getEdges().put(vertexTo.getId(), edge);
        vertexTo.getEdges().put(vertexFrom.getId(), edge);
        //изменится ли обхект в сете, если изменить его вне его?...
        super.getVertexes().addAll(asList(vertexFrom, vertexTo));
    }

    @Override
    public SimpleVertex getVertexById(UUID id) {
        return super.getVertexes()
                .stream()
                .filter(vertex -> vertex.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private boolean isEdgeAlreadyExists(@NonNull SimpleVertex vertexFrom, @NonNull SimpleVertex vertexTo, @NonNull NonWeightedEdge edge) {
        final Optional<NonWeightedEdge> edgVertexFrom = Optional.ofNullable(vertexFrom.getEdges().get(vertexTo.getId()));
        final Optional<NonWeightedEdge> edgVertexTo = Optional.ofNullable(vertexTo.getEdges().get(vertexFrom.getId()));
        return edgVertexFrom.isPresent() || edgVertexTo.isPresent();
    }
}
