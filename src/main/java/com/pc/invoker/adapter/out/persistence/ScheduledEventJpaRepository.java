package com.pc.invoker.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduledEventJpaRepository extends JpaRepository<ScheduledEventEntity, UUID> {
}
