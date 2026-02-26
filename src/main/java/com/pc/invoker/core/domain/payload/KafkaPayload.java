package com.pc.invoker.core.domain.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public final class KafkaPayload implements Payload {
    private final String topic;
    private final String key;
    private final String value;
}
