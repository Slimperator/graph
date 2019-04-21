package com.graph.core.dataaccess.entity.vertex;

import com.graph.core.dataaccess.entity.edge.Edge;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.Map;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleVertex extends Vertex {
    private String name;

    public SimpleVertex(@NonNull Map<UUID, Edge> edges, @NonNull String name) {
        super(edges);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
