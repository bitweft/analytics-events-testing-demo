package com.viu.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public record ExpectedEventDetails(
        @JsonProperty("eventName")String eventName,
        @JsonProperty("keysPresenceMatch")List<String>keysPresenceMatch,
        @JsonProperty("exactMatch")List<ExactMatch>exactMatch
) {
}