package com.graph.core.strategy;

import com.graph.core.dataaccess.entity.graph.AbstractGraph;
import com.graph.core.dataaccess.entity.path.SimplePath;
import com.graph.core.dataaccess.entity.vertex.Vertex;
import lombok.NonNull;

public interface PathfinderStrategy {

    SimplePath findPath(@NonNull AbstractGraph graph, @NonNull Vertex startVertex, @NonNull Vertex endVertex);

}
