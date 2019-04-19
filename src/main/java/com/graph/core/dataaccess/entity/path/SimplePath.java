package com.graph.core.dataaccess.entity.path;

import com.graph.core.dataaccess.entity.edge.NonWeightedEdge;
import com.graph.core.dataaccess.entity.vertex.SimpleVertex;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class SimplePath {
    private Map<SimpleVertex, NonWeightedEdge> path;
}
