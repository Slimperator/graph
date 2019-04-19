package com.graph.core.strategy;

import com.graph.core.dataaccess.entity.edge.EdgeDirection;
import com.graph.core.dataaccess.entity.edge.NonWeightedEdge;
import com.graph.core.dataaccess.entity.graph.AbstractGraph;
import com.graph.core.dataaccess.entity.path.SimplePath;
import com.graph.core.dataaccess.entity.vertex.SimpleVertex;
import lombok.NonNull;

import java.util.*;

public class ForceEnumerationStrategy implements PathfinderStrategy {
    private static final String VERTEXES_DOES_NOT_EXISTS_MASSAGE = "Vertexes doesn't exists";
    private Set<SimpleVertex> visitedVertexes = new HashSet<>();
    private SimplePath path = new SimplePath(new HashMap<>());

    public SimplePath findPath(@NonNull AbstractGraph graph, @NonNull SimpleVertex startVertex, @NonNull SimpleVertex endVertex) {
        if (!graph.isGraphContainVertexes(startVertex, endVertex)) {
            throw new IllegalArgumentException(VERTEXES_DOES_NOT_EXISTS_MASSAGE);
        }
        forceEnumeration(graph, startVertex, endVertex);
        return path;
    }

    private boolean forceEnumeration(AbstractGraph graph, SimpleVertex startVertex, SimpleVertex endVertex) {
        //Если вершина уже помечена - возвращаем фелс
        if (visitedVertexes.contains(startVertex)) {
            return false;
        } else {
            // иначе помечаем вершину
            visitedVertexes.add(startVertex);
        }
        // если вершина связана с искомой, начинаем формировать путь и сохраняем вершину с ребром в мапу
        if (startVertex.getEdges().containsKey(endVertex.getId()) && isEdgeDirectionFromVertex(startVertex.getEdges().get(endVertex.getId()))) {
            path.getPath().put(startVertex, startVertex.getEdges().get(endVertex.getId()));
            return true;
        } else {
            // иначе обходим все ребра в поисках нужной
            for (Map.Entry<UUID, NonWeightedEdge> connection: startVertex.getEdges().entrySet()) {
                //достаем вершину из родителя
                SimpleVertex vertex = graph.getVertexById(connection.getKey());
                //если направление связи из вершины, тогда
                //рекурсивно погружаем её в этот же метод
                //если путь нашелся, помещаем в путь данную промежуточную вершину и пробрасываем выше
                if (isEdgeDirectionFromVertex(connection.getValue())) {
                    if (forceEnumeration(graph, vertex, endVertex)) {
                        path.getPath().put(startVertex, startVertex.getEdges().get(vertex.getId()));
                        return true;
                    }
                }
            }
        }
        //если не нашли путь, возвращаем фелс
        return false;
    }

    private boolean isEdgeDirectionFromVertex(NonWeightedEdge edge) {
        return edge.getDirection().equals(EdgeDirection.ABOTH) || edge.getDirection().equals(EdgeDirection.DIRECT);
    }
}
