package com.pc.invoker.core.domain.payload;

public sealed interface Payload permits ApiPayload, KafkaPayload {
}
