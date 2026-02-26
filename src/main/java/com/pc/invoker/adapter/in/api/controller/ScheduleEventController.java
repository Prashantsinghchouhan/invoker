package com.pc.invoker.adapter.in.api.controller;

import com.pc.invoker.adapter.in.mapper.ScheduleEventApiMapper;
import com.pc.invoker.adapter.in.api.dto.request.ScheduleEventRequest;
import com.pc.invoker.adapter.in.api.dto.response.ScheduleEventResponse;
import com.pc.invoker.core.domain.ScheduledEvent;
import com.pc.invoker.core.port.in.ScheduleEventUseCase;
import com.pc.invoker.core.port.in.command.ScheduleEventCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/scheduled-events")
@RequiredArgsConstructor
public class ScheduleEventController {

    private final ScheduleEventUseCase scheduleEventUseCase;

    @PostMapping
    public ResponseEntity<ScheduleEventResponse> schedule(
            @RequestBody ScheduleEventRequest request) {
        ScheduleEventCommand command = ScheduleEventApiMapper.toCommand(request);
        ScheduledEvent event = scheduleEventUseCase.schedule(command);
        ScheduleEventResponse response = ScheduleEventApiMapper.toResponse(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
