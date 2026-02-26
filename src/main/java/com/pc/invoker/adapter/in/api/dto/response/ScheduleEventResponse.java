package com.pc.invoker.adapter.in.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class ScheduleEventResponse {
    private final UUID id;
    private final String channel;
    private final String scheduleType;
    private final String status;
    private final Instant scheduledAt;
    private final Instant createdAt;
}
