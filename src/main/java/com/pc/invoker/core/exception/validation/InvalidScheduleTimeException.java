package com.pc.invoker.core.exception.validation;

import com.pc.invoker.core.exception.ErrorCodes;
import com.pc.invoker.core.exception.ErrorMessages;

public class InvalidScheduleTimeException extends ValidationException {

    private InvalidScheduleTimeException(String message, String errorCode) {
        super(message, errorCode);
    }

    public static InvalidScheduleTimeException nullOrPast() {
        return new InvalidScheduleTimeException(
                ErrorMessages.SCHEDULE_TIME_INVALID, ErrorCodes.INVALID_SCHEDULE_TIME);
    }
}
