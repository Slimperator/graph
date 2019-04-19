package com.graph.core.dataaccess.entity.vertex;

import com.graph.core.dataaccess.entity.AbstractEntity;
import com.graph.core.dataaccess.entity.edge.NonWeightedEdge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SimpleVertex extends AbstractEntity {
    private String name;
    private Map<UUID, NonWeightedEdge> edges;
}
