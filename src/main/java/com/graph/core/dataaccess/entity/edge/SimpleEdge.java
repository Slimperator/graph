package com.graph.core.dataaccess.entity.edge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class SimpleEdge extends Edge {
    private final EdgeDirection direction;

    @Override
    public Edge reverseEdge() {
        EdgeDirection reversedEdgeDirection;
        switch (direction) {
            case DIRECT:
                reversedEdgeDirection = EdgeDirection.REVERSE;
                break;
            case REVERSE:
                reversedEdgeDirection = EdgeDirection.DIRECT;
                break;
            default:
                reversedEdgeDirection = EdgeDirection.ABOTH;
                break;
        }
        return new SimpleEdge(reversedEdgeDirection);
    }
}
