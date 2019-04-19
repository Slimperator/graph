package com.graph.core;

import com.graph.core.dataaccess.entity.edge.EdgeDirection;
import com.graph.core.dataaccess.entity.edge.NonWeightedEdge;
import com.graph.core.dataaccess.entity.graph.AbstractGraph;
import com.graph.core.dataaccess.entity.graph.UnderectedGraph;
import com.graph.core.dataaccess.entity.path.SimplePath;
import com.graph.core.dataaccess.entity.vertex.SimpleVertex;
import com.graph.core.strategy.PathfinderStrategy;
import com.graph.core.strategy.ForceEnumerationStrategy;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class SimpleTest {
    @Test
    public void simpleTest() {
        SimpleVertex vertex1 = new SimpleVertex("1", new HashMap<>());
        SimpleVertex vertex2 = new SimpleVertex("2", new HashMap<>());
        SimpleVertex vertex3 = new SimpleVertex("3", new HashMap<>());
        SimpleVertex vertex4 = new SimpleVertex("4", new HashMap<>());

        NonWeightedEdge v1v2 = new NonWeightedEdge(EdgeDirection.ABOTH);
        NonWeightedEdge v1v3 = new NonWeightedEdge(EdgeDirection.ABOTH);
        NonWeightedEdge v3v4 = new NonWeightedEdge(EdgeDirection.ABOTH);
        PathfinderStrategy strategy = new ForceEnumerationStrategy();

        AbstractGraph graph = new UnderectedGraph(new HashSet<>(Arrays.asList(vertex1, vertex2,vertex3,vertex4)),
                new HashSet<>());

        graph.addEdge(vertex1, vertex2, v1v2);
        graph.addEdge(vertex1, vertex3, v1v3);
        graph.addEdge(vertex3, vertex4, v3v4);

        SimplePath path = graph.findPath(vertex1, vertex4, strategy);
    }
}
