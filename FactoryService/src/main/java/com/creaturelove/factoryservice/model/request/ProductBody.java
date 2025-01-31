package com.creaturelove.factoryservice.model.request;

import java.math.BigDecimal;

public record ProductBody(
        String name,
        String description,
        BigDecimal price,
        String category,
        String avatar_url,
        Integer stock
){}
