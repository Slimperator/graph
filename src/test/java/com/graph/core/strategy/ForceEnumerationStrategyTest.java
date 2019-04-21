package com.graph.core.strategy;

import com.graph.core.dataaccess.entity.edge.Edge;
import com.graph.core.dataaccess.entity.edge.EdgeDirection;
import com.graph.core.dataaccess.entity.edge.SimpleEdge;
import com.graph.core.dataaccess.entity.graph.AbstractGraph;
import com.graph.core.dataaccess.entity.graph.DirectedGraph;
import com.graph.core.dataaccess.entity.graph.UndirectedGraph;
import com.graph.core.dataaccess.entity.path.SimplePath;
import com.graph.core.dataaccess.entity.vertex.SimpleVertex;
import com.graph.core.dataaccess.entity.vertex.Vertex;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class ForceEnumerationStrategyTest {

    @Test
    public void findPathInUndirectedGraphTest() {
        final Vertex vertex1 = new SimpleVertex(new HashMap<>(), "1");
        final Vertex vertex2 = new SimpleVertex(new HashMap<>(), "2");
        final Vertex vertex3 = new SimpleVertex(new HashMap<>(), "3");
        final Vertex vertex4 = new SimpleVertex(new HashMap<>(), "4");
        final Edge v1v2 = new SimpleEdge(EdgeDirection.ABOTH);
        final Edge v1v3 = new SimpleEdge(EdgeDirection.ABOTH);
        final Edge v3v4 = new SimpleEdge(EdgeDirection.ABOTH);
        final PathfinderStrategy strategy = new ForceEnumerationStrategy();
        final AbstractGraph graph = new UndirectedGraph(new HashSet<>(Arrays.asList(vertex1, vertex2, vertex3, vertex4)));

        graph.addEdge(vertex1, vertex2, v1v2);
        graph.addEdge(vertex1, vertex3, v1v3);
        graph.addEdge(vertex3, vertex4, v3v4);
        final SimplePath template = new SimplePath(Arrays.asList(vertex1, vertex3, vertex4), Arrays.asList(v1v3, v3v4, v3v4));
        final SimplePath result = graph.findPath(vertex1, vertex4, strategy);

        assertTrue(equalsOfPaths(result, template));
    }

    @Test
    public void findPathInDirectedGraphTest() {
        final Vertex vertex1 = new SimpleVertex(new HashMap<>(), "1");
        final Vertex vertex2 = new SimpleVertex(new HashMap<>(), "2");
        final Vertex vertex3 = new SimpleVertex(new HashMap<>(), "3");
        final Vertex vertex4 = new SimpleVertex(new HashMap<>(), "4");
        final Edge v1v2 = new SimpleEdge(EdgeDirection.ABOTH);
        final Edge v1v3 = new SimpleEdge(EdgeDirection.DIRECT);
        final Edge v3v4 = new SimpleEdge(EdgeDirection.DIRECT);
        final Edge v4v3 = new SimpleEdge(EdgeDirection.REVERSE);

        final PathfinderStrategy strategy = new ForceEnumerationStrategy();
        final AbstractGraph graph = new DirectedGraph(new HashSet<>(Arrays.asList(vertex1, vertex2, vertex3, vertex4)));

        graph.addEdge(vertex1, vertex2, v1v2);
        graph.addEdge(vertex1, vertex3, v1v3);
        graph.addEdge(vertex3, vertex4, v3v4);

        final SimplePath template = new SimplePath(Arrays.asList(vertex1, vertex3, vertex4), Arrays.asList(v1v3, v3v4, v4v3));
        final SimplePath result = graph.findPath(vertex1, vertex4, strategy);

        assertTrue(equalsOfPaths(result, template));
    }

    private boolean equalsOfPaths(final SimplePath result, final SimplePath template) {
        final List<Edge> resultEdges = result.getEdges();
        final List<Edge> templateEdges = template.getEdges();
        final List<Vertex> resultVertex = result.getVertices();
        final List<Vertex> templateVertex = template.getVertices();

        for (int i = 0; i < result.getVertices().size(); i++) {
            if (!resultVertex.get(i)
                    .equals(templateVertex.get(i))
                    && resultEdges.get(i)
                    .getDirection()
                    .equals(templateEdges.get(i)
                            .getDirection())) {
                return false;
            }
        }
        return true;
    }
}
