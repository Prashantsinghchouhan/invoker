package com.pc.invoker.adapter.in.mapper;

import com.pc.invoker.adapter.in.kafka.dto.ScheduleEventMessage;
import com.pc.invoker.core.domain.enums.Channel;
import com.pc.invoker.core.domain.enums.ScheduleType;
import com.pc.invoker.core.domain.payload.ApiPayload;
import com.pc.invoker.core.domain.payload.KafkaPayload;
import com.pc.invoker.core.domain.payload.Payload;
import com.pc.invoker.core.port.in.command.ScheduleEventCommand;

public final class ScheduleEventKafkaMapper {

    private ScheduleEventKafkaMapper() {}

    public static ScheduleEventCommand toCommand(ScheduleEventMessage message) {
        Channel channel = parseEnum(Channel.class, message.getChannel());
        ScheduleType scheduleType = parseEnum(ScheduleType.class, message.getScheduleType());
        Payload payload = toPayload(message, scheduleType);

        return ScheduleEventCommand.builder()
                .eventId(message.getEventId())
                .channel(channel)
                .scheduleType(scheduleType)
                .payload(payload)
                .scheduledAt(message.getScheduledAt())
                .build();
    }

    private static Payload toPayload(ScheduleEventMessage message, ScheduleType scheduleType) {
        if (scheduleType == null) {
            return null;
        }
        return switch (scheduleType) {
            case API -> mapApiPayload(message.getApiPayload());
            case KAFKA -> mapKafkaPayload(message.getKafkaPayload());
        };
    }

    private static ApiPayload mapApiPayload(ScheduleEventMessage.ApiPayloadMessage req) {
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

    private static KafkaPayload mapKafkaPayload(ScheduleEventMessage.KafkaPayloadMessage req) {
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
