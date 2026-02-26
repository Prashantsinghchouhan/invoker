package com.pc.invoker.adapter.in.mapper;

import com.pc.invoker.adapter.in.api.dto.response.ScheduleEventResponse;
import com.pc.invoker.adapter.in.api.dto.request.ApiPayloadRequest;
import com.pc.invoker.adapter.in.api.dto.request.KafkaPayloadRequest;
import com.pc.invoker.adapter.in.api.dto.request.ScheduleEventRequest;
import com.pc.invoker.core.domain.ScheduledEvent;
import com.pc.invoker.core.domain.enums.Channel;
import com.pc.invoker.core.domain.enums.ScheduleType;
import com.pc.invoker.core.domain.payload.ApiPayload;
import com.pc.invoker.core.domain.payload.KafkaPayload;
import com.pc.invoker.core.domain.payload.Payload;
import com.pc.invoker.core.port.in.command.ScheduleEventCommand;

public final class ScheduleEventApiMapper {

    private ScheduleEventApiMapper() {}

    public static ScheduleEventCommand toCommand(ScheduleEventRequest request) {
        Channel channel = parseEnum(Channel.class, request.getChannel());
        ScheduleType scheduleType = parseEnum(ScheduleType.class, request.getScheduleType());
        Payload payload = toPayload(request, scheduleType);

        return ScheduleEventCommand.builder()
                .eventId(request.getEventId())
                .channel(channel)
                .scheduleType(scheduleType)
                .payload(payload)
                .scheduledAt(request.getScheduledAt())
                .build();
    }

    public static ScheduleEventResponse toResponse(ScheduledEvent event) {
        return ScheduleEventResponse.builder()
                .id(event.getId())
                .channel(event.getChannel().name())
                .scheduleType(event.getScheduleType().name())
                .status(event.getStatus().name())
                .scheduledAt(event.getScheduledAt())
                .createdAt(event.getCreatedAt())
                .build();
    }

    private static Payload toPayload(ScheduleEventRequest request, ScheduleType scheduleType) {
        if (scheduleType == null) {
            return null;
        }
        return switch (scheduleType) {
            case API -> mapApiPayload(request.getApiPayload());
            case KAFKA -> mapKafkaPayload(request.getKafkaPayload());
        };
    }

    private static ApiPayload mapApiPayload(ApiPayloadRequest req) {
        if (req == null) {
            return null;
        }
        return ApiPayload.builder()
                .url(req.getUrl())
                .method(req.getMethod())
                .headers(req.getHeaders())
                .body(req.getBody())
                .build();
    }

    private static KafkaPayload mapKafkaPayload(KafkaPayloadRequest req) {
        if (req == null) {
            return null;
        }
        return KafkaPayload.builder()
                .topic(req.getTopic())
                .key(req.getKey())
                .value(req.getValue())
                .build();
    }

    private static <E extends Enum<E>> E parseEnum(Class<E> enumType, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Enum.valueOf(enumType, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
