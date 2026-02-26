package com.pc.invoker.adapter.out.persistence;

import com.pc.invoker.core.domain.ScheduledEvent;
import com.pc.invoker.core.port.out.ScheduledEventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ScheduledEventPersistenceAdapter implements ScheduledEventRepositoryPort {

    private final ScheduledEventJpaRepository jpaRepository;

    @Override
    public ScheduledEvent save(ScheduledEvent event) {
        ScheduledEventEntity entity = ScheduledEventMapper.toEntity(event);
        ScheduledEventEntity saved = jpaRepository.save(entity);
        return ScheduledEventMapper.toDomain(saved);
    }

    @Override
    public Optional<ScheduledEvent> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(ScheduledEventMapper::toDomain);
    }
}
