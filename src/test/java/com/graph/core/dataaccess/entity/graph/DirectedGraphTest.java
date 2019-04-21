package com.graph.core.dataaccess.entity.graph;

import com.graph.core.dataaccess.entity.edge.Edge;
import com.graph.core.dataaccess.entity.edge.EdgeDirection;
import com.graph.core.dataaccess.entity.edge.SimpleEdge;
import com.graph.core.dataaccess.entity.vertex.SimpleVertex;
import com.graph.core.dataaccess.entity.vertex.Vertex;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

public class DirectedGraphTest {

    @Test(expected=IllegalArgumentException.class)
    public void addAlreadyExistsVertexTest() {
        final Vertex vertex1 = new SimpleVertex(new HashMap<>(), "1");
        final AbstractGraph graph = new DirectedGraph(new HashSet<>());
        graph.addVertex(vertex1);
        graph.addVertex(vertex1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addAlreadyExistsEdgeTest() {
        final Vertex vertex1 = new SimpleVertex(new HashMap<>(), "1");
        final Edge edge1 = new SimpleEdge(EdgeDirection.ABOTH);
        final AbstractGraph graph = new DirectedGraph(new HashSet<>());
        graph.addVertex(vertex1);
        graph.addEdge(vertex1, vertex1, edge1);
        graph.addEdge(vertex1, vertex1, edge1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addVertexToItselfEdgeWithOneDirectionTest() {
        final Vertex vertex1 = new SimpleVertex(new HashMap<>(), "1");
        final Edge edge1 = new SimpleEdge(EdgeDirection.DIRECT);
        final AbstractGraph graph = new DirectedGraph(new HashSet<>());
        graph.addVertex(vertex1);
        graph.addEdge(vertex1, vertex1, edge1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addVertexToVertexEdgeIfVertexesDoesNotExistsTest() {
        final Vertex vertex1 = new SimpleVertex(new HashMap<>(), "1");
        final Edge edge1 = new SimpleEdge(EdgeDirection.ABOTH);
        final AbstractGraph graph = new DirectedGraph(new HashSet<>());
        graph.addEdge(vertex1, vertex1, edge1);
    }
}
