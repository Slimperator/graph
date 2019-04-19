package com.graph.core.strategy;

import com.graph.core.dataaccess.entity.graph.AbstractGraph;
import com.graph.core.dataaccess.entity.path.SimplePath;
import com.graph.core.dataaccess.entity.vertex.SimpleVertex;

public interface PathfinderStrategy {
    SimplePath findPath(AbstractGraph graph, SimpleVertex startVertex, SimpleVertex endVertex);
}
