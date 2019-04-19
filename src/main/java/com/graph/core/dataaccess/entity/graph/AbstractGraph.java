package com.graph.core.dataaccess.entity.graph;

import com.graph.core.dataaccess.entity.AbstractEntity;
import com.graph.core.dataaccess.entity.edge.EdgeDirection;
import com.graph.core.dataaccess.entity.edge.NonWeightedEdge;
import com.graph.core.dataaccess.entity.path.SimplePath;
import com.graph.core.dataaccess.entity.vertex.SimpleVertex;
import com.graph.core.strategy.PathfinderStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import sun.security.provider.certpath.Vertex;

import java.util.Set;
import java.util.UUID;

import static java.util.Arrays.asList;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractGraph extends AbstractEntity {
    private Set<SimpleVertex> vertexes;
    private Set<NonWeightedEdge> edges;

    public abstract void addVertex(@NonNull SimpleVertex vertex);

    public abstract SimpleVertex getVertexById(UUID id);

    public abstract void addEdge(@NonNull SimpleVertex vertexFrom, @NonNull SimpleVertex vertexTo, @NonNull NonWeightedEdge edge);

    public SimplePath findPath(@NonNull SimpleVertex vertexFrom, @NonNull SimpleVertex vertexTo, @NonNull PathfinderStrategy strategy) {
        return strategy.findPath(this, vertexFrom, vertexTo);
    }

    public boolean isGraphContainVertexes(@NonNull SimpleVertex... simpleVertexes) {
        return vertexes.containsAll(asList(simpleVertexes));
    }
}
