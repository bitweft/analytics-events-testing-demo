package com.viu.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExactMatch(
        @JsonProperty("key")String key,
        @JsonProperty("value")String value
) {
}
