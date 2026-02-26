package com.pc.invoker.core.domain;

import com.pc.invoker.core.domain.enums.Channel;
import com.pc.invoker.core.domain.enums.EventStatus;
import com.pc.invoker.core.domain.enums.ScheduleType;
import com.pc.invoker.core.domain.payload.Payload;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class ScheduledEvent {
    private final UUID id;
    private final Channel channel;
    private final ScheduleType scheduleType;
    private final Payload payload;
    private final EventStatus status;
    private final Instant scheduledAt;
    private final Instant nextAttemptAt;
    private final int retryCount;
    private final int maxRetries;
    private final Instant lockedAt;
    private final Instant createdAt;
    private final Instant updatedAt;

    public static ScheduledEvent createNew(UUID eventId,
                                           Channel channel,
                                           ScheduleType scheduleType,
                                           Payload payload,
                                           Instant scheduledAt,
                                           int maxRetries) {
        Instant now = Instant.now();
        return ScheduledEvent.builder()
                .id(eventId)
                .channel(channel)
                .scheduleType(scheduleType)
                .payload(payload)
                .maxRetries(maxRetries)
                .scheduledAt(scheduledAt)
                .nextAttemptAt(scheduledAt)
                .status(EventStatus.PENDING)
                .retryCount(0)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
}
