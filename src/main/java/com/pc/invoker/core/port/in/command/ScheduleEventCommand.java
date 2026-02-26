package com.pc.invoker.core.port.in.command;

import com.pc.invoker.core.domain.enums.Channel;
import com.pc.invoker.core.domain.enums.ScheduleType;
import com.pc.invoker.core.domain.payload.Payload;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class ScheduleEventCommand {
    private final UUID eventId;
    private final Channel channel;
    private final ScheduleType scheduleType;
    private final Payload payload;
    private final Instant scheduledAt;
}
