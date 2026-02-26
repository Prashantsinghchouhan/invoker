package com.pc.invoker.adapter.in.kafka.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ScheduleEventMessage {
    private UUID eventId;
    private String channel;
    private String scheduleType;
    private Instant scheduledAt;
    private ApiPayloadMessage apiPayload;
    private KafkaPayloadMessage kafkaPayload;

    @Getter
    @NoArgsConstructor
    public static class ApiPayloadMessage {
        private String url;
        private String method;
        private Map<String, String> headers;
        private String body;
    }

    @Getter
    @NoArgsConstructor
    public static class KafkaPayloadMessage {
        private String topic;
        private String key;
        private String value;
    }
}
