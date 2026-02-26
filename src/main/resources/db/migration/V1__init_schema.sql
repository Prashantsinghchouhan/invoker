CREATE TABLE scheduled_events (
    id                UUID            PRIMARY KEY,
    channel           VARCHAR(20),
    schedule_type     VARCHAR(20)     NOT NULL,
    payload           JSONB           NOT NULL,
    status            VARCHAR(20)     NOT NULL,
    scheduled_at      TIMESTAMP       NOT NULL,
    next_attempt_at   TIMESTAMP       NOT NULL,
    retry_count       INT             DEFAULT 0,
    max_retries       INT             DEFAULT 5,
    locked_at         TIMESTAMP       NULL,
    created_at        TIMESTAMP       NOT NULL,
    updated_at        TIMESTAMP       NOT NULL
);

CREATE INDEX idx_sched_ready
    ON scheduled_events (next_attempt_at)
    WHERE status IN ('PENDING', 'FAILED');

CREATE INDEX idx_sched_status
    ON scheduled_events (status);
