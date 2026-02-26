package com.pc.invoker.core.exception;

public final class ErrorCodes {
    private ErrorCodes() {}

    // Validation
    public static final String INVALID_EVENT_ID = "INVALID_EVENT_ID";
    public static final String INVALID_CHANNEL = "INVALID_CHANNEL";
    public static final String INVALID_SCHEDULE_TYPE = "INVALID_SCHEDULE_TYPE";
    public static final String INVALID_SCHEDULE_TIME = "INVALID_SCHEDULE_TIME";
    public static final String INVALID_PAYLOAD = "INVALID_PAYLOAD";

    // Not found
    public static final String EVENT_NOT_FOUND = "EVENT_NOT_FOUND";

    // Conflict
    public static final String DUPLICATE_EVENT = "DUPLICATE_EVENT";

    // System
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
}
