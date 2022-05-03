package com.vorkurt.entity.transport.pack.format;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TypePck {
    @JsonProperty("STANDARD")
    STANDARD,
    @JsonProperty("SMALL")
    SMALL,
    @JsonProperty("LARGE")
    LARGE,
    @JsonProperty("HEAVY_LARGE")
    HEAVY_LARGE
}
