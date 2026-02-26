package com.pc.invoker.core.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Getter
@Builder
@Jacksonized
public final class ApiPayload implements Payload {
    private final String url;
    private final String method;
    private final Map<String, String> headers;
    private final String body;
}
