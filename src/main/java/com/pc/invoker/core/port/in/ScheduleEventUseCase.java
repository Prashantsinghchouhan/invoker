package com.pc.invoker.core.port.in;

import com.pc.invoker.core.domain.ScheduledEvent;
import com.pc.invoker.core.port.in.command.ScheduleEventCommand;

public interface ScheduleEventUseCase {

    ScheduledEvent schedule(ScheduleEventCommand command);
}
