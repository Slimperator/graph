package com.graph.core.dataaccess.entity;

import lombok.*;

import java.util.UUID;

@Data
public abstract class AbstractEntity {
    @NonNull
    private final UUID id = UUID.randomUUID();
}
