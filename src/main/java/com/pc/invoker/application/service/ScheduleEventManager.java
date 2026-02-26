package com.pc.invoker.application.service;

import com.pc.invoker.core.domain.enums.Channel;
import com.pc.invoker.core.domain.enums.ScheduleType;
import com.pc.invoker.core.domain.ScheduledEvent;
import com.pc.invoker.core.domain.payload.Payload;
import com.pc.invoker.core.exception.validation.InvalidChannelException;
import com.pc.invoker.core.exception.validation.InvalidEventIdException;
import com.pc.invoker.core.exception.validation.InvalidPayloadException;
import com.pc.invoker.core.exception.validation.InvalidScheduleTimeException;
import com.pc.invoker.core.exception.validation.InvalidScheduleTypeException;
import com.pc.invoker.core.port.in.ScheduleEventUseCase;
import com.pc.invoker.core.port.in.command.ScheduleEventCommand;
import com.pc.invoker.core.port.out.ScheduledEventRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class ScheduleEventManager implements ScheduleEventUseCase {

    private final ScheduledEventRepositoryPort repository;
    private final int maxRetries;

    @Override
    public ScheduledEvent schedule(ScheduleEventCommand command) {
        UUID eventId = command.getEventId();
        if (eventId == null) {
            throw InvalidEventIdException.nullEventId();
        }

        Optional<ScheduledEvent> existing = repository.findById(eventId);
        if (existing.isPresent()) {
            log.info("Duplicate event detected, returning existing: id={}", eventId);
            return existing.get();
        }

        Channel channel = command.getChannel();
        if (channel == null) {
            throw InvalidChannelException.nullChannel();
        }

        ScheduleType scheduleType = command.getScheduleType();
        if (scheduleType == null) {
            throw InvalidScheduleTypeException.nullScheduleType();
        }

        Payload payload = command.getPayload();
        if (payload == null) {
            throw InvalidPayloadException.nullPayload();
        }

        Instant scheduledAt = command.getScheduledAt();
        if (scheduledAt == null || scheduledAt.isBefore(Instant.now())) {
            throw InvalidScheduleTimeException.nullOrPast();
        }

        ScheduledEvent event = ScheduledEvent.createNew(
                eventId, channel, scheduleType, payload, scheduledAt, maxRetries
        );

        ScheduledEvent saved = repository.save(event);
        log.info("Scheduled event created: id={}, channel={}, scheduledAt={}",
                saved.getId(), saved.getChannel(), saved.getScheduledAt());
        return saved;
    }
}
