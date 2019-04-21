package com.graph.core.dataaccess.entity.edge;

import com.graph.core.dataaccess.entity.AbstractEntity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public abstract class Edge extends AbstractEntity {

    public abstract EdgeDirection getDirection();

    public abstract Edge reverseEdge();

}
