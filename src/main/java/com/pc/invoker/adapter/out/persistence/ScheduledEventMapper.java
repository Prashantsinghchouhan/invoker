package com.pc.invoker.adapter.out.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pc.invoker.core.domain.enums.Channel;
import com.pc.invoker.core.domain.enums.EventStatus;
import com.pc.invoker.core.domain.enums.ScheduleType;
import com.pc.invoker.core.domain.ScheduledEvent;
import com.pc.invoker.core.domain.payload.ApiPayload;
import com.pc.invoker.core.domain.payload.KafkaPayload;
import com.pc.invoker.core.domain.payload.Payload;

public final class ScheduledEventMapper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private ScheduledEventMapper() {}

    public static ScheduledEventEntity toEntity(ScheduledEvent domain) {
        return ScheduledEventEntity.builder()
                .id(domain.getId())
                .channel(domain.getChannel().name())
                .scheduleType(domain.getScheduleType().name())
                .payload(serializePayload(domain.getPayload()))
                .status(domain.getStatus().name())
                .scheduledAt(domain.getScheduledAt())
                .nextAttemptAt(domain.getNextAttemptAt())
                .retryCount(domain.getRetryCount())
                .maxRetries(domain.getMaxRetries())
                .lockedAt(domain.getLockedAt())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    public static ScheduledEvent toDomain(ScheduledEventEntity entity) {
        ScheduleType scheduleType = ScheduleType.valueOf(entity.getScheduleType());
        return ScheduledEvent.builder()
                .id(entity.getId())
                .channel(entity.getChannel() != null ? Channel.valueOf(entity.getChannel()) : null)
                .scheduleType(scheduleType)
                .payload(deserializePayload(entity.getPayload(), scheduleType))
                .status(EventStatus.valueOf(entity.getStatus()))
                .scheduledAt(entity.getScheduledAt())
                .nextAttemptAt(entity.getNextAttemptAt())
                .retryCount(entity.getRetryCount())
                .maxRetries(entity.getMaxRetries())
                .lockedAt(entity.getLockedAt())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private static String serializePayload(Payload payload) {
        try {
            return OBJECT_MAPPER.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to serialize payload", e);
        }
    }

    private static Payload deserializePayload(String json, ScheduleType scheduleType) {
        try {
            return switch (scheduleType) {
                case API -> OBJECT_MAPPER.readValue(json, ApiPayload.class);
                case KAFKA -> OBJECT_MAPPER.readValue(json, KafkaPayload.class);
            };
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to deserialize payload", e);
        }
    }
}
