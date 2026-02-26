package com.pc.invoker.config;

import com.pc.invoker.application.service.ScheduleEventManager;
import com.pc.invoker.core.port.in.ScheduleEventUseCase;
import com.pc.invoker.core.port.out.ScheduledEventRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleEventConfig {

    @Bean
    public ScheduleEventUseCase scheduleEventUseCase(
            ScheduledEventRepositoryPort repository,
            @Value("${invoker.scheduler.max-retries}") int maxRetries) {
        return new ScheduleEventManager(repository, maxRetries);
    }
}
