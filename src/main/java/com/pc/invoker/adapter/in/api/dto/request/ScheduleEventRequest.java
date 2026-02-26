package com.pc.invoker.adapter.in.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ScheduleEventRequest {
    private UUID eventId;
    private String channel;
    private String scheduleType;
    private Instant scheduledAt;
    private ApiPayloadRequest apiPayload;
    private KafkaPayloadRequest kafkaPayload;
}
