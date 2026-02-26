package com.pc.invoker.core.exception.validation;

import com.pc.invoker.core.exception.ErrorCodes;
import com.pc.invoker.core.exception.ErrorMessages;

public class InvalidScheduleTypeException extends ValidationException {

    private InvalidScheduleTypeException(String message, String errorCode) {
        super(message, errorCode);
    }

    public static InvalidScheduleTypeException nullScheduleType() {
        return new InvalidScheduleTypeException(
                ErrorMessages.SCHEDULE_TYPE_REQUIRED, ErrorCodes.INVALID_SCHEDULE_TYPE);
    }
}
