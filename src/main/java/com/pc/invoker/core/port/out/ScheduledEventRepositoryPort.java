package com.pc.invoker.core.port.out;

import com.pc.invoker.core.domain.ScheduledEvent;

import java.util.Optional;
import java.util.UUID;

public interface ScheduledEventRepositoryPort {
    ScheduledEvent save(ScheduledEvent event);
    Optional<ScheduledEvent> findById(UUID id);
}
