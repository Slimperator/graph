package com.graph.core.dataaccess.entity.edge;

import com.graph.core.dataaccess.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class NonWeightedEdge extends AbstractEntity { //название поменять
    private final EdgeDirection direction;
}
