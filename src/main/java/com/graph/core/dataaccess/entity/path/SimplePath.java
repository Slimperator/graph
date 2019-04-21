package com.graph.core.dataaccess.entity.path;

import com.graph.core.dataaccess.entity.edge.Edge;
import com.graph.core.dataaccess.entity.vertex.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class SimplePath {
    private List<Vertex> vertices;
    private List<Edge> edges;

    /**
     * Change the order of the path
     */
    public void reversePath() {
        Collections.reverse(vertices);
        Collections.reverse(edges);
    }
}
