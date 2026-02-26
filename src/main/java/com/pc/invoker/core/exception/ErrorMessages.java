package com.pc.invoker.core.exception;

public final class ErrorMessages {
    private ErrorMessages() {}

    // Validation
    public static final String EVENT_ID_REQUIRED = "Event ID cannot be null";
    public static final String CHANNEL_REQUIRED = "Channel cannot be null";
    public static final String SCHEDULE_TYPE_REQUIRED = "Schedule type cannot be null";
    public static final String SCHEDULE_TIME_INVALID =
            "Scheduled time must be non-null and in the future";
    public static final String PAYLOAD_REQUIRED = "Payload cannot be null";

    // Not found
    public static final String EVENT_NOT_FOUND = "Scheduled event %s not found";

    // System
    public static final String INTERNAL_SERVER_ERROR = "Unexpected system failure";
}
