package com.graph.core.dataaccess.entity.vertex;

import com.graph.core.dataaccess.entity.AbstractEntity;
import com.graph.core.dataaccess.entity.edge.Edge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Vertex extends AbstractEntity {
    private static final String A_VERTEX_HAS_NO_NAME = "Vertex has no name (c) Arya Stark";
    private Map<UUID, Edge> edges;

    public String getName() {
        return A_VERTEX_HAS_NO_NAME;
    }

}
