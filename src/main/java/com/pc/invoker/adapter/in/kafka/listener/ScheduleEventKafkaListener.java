package com.pc.invoker.adapter.in.kafka.listener;

import com.pc.invoker.adapter.in.kafka.dto.ScheduleEventMessage;
import com.pc.invoker.adapter.in.mapper.ScheduleEventKafkaMapper;
import com.pc.invoker.core.domain.ScheduledEvent;
import com.pc.invoker.core.port.in.ScheduleEventUseCase;
import com.pc.invoker.core.port.in.command.ScheduleEventCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleEventKafkaListener {

    private final ScheduleEventUseCase scheduleEventUseCase;

    @KafkaListener(
            topics = "${invoker.kafka.topic.schedule-events}",
            groupId = "${invoker.kafka.consumer.group-id}",
            errorHandler = "kafkaErrorHandler"
    )
    public void onScheduleEvent(ScheduleEventMessage message, Acknowledgment ack) {
        log.info("Received schedule event from Kafka: eventId={}",
                message.getEventId());

        ScheduleEventCommand command = ScheduleEventKafkaMapper.toCommand(message);
        ScheduledEvent event = scheduleEventUseCase.schedule(command);

        ack.acknowledge();
        log.info("Kafka offset committed for event: id={}", event.getId());
    }
}
