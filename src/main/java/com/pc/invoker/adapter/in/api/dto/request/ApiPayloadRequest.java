package com.pc.invoker.adapter.in.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class ApiPayloadRequest {
    private String url;
    private String method;
    private Map<String, String> headers;
    private String body;
}
