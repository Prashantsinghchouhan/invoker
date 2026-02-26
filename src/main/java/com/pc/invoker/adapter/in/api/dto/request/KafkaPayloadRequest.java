package com.pc.invoker.adapter.in.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KafkaPayloadRequest {
    private String topic;
    private String key;
    private String value;
}
