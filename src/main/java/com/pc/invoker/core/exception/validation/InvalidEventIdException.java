package com.pc.invoker.core.exception.validation;

import com.pc.invoker.core.exception.ErrorCodes;
import com.pc.invoker.core.exception.ErrorMessages;

public class InvalidEventIdException extends ValidationException {

    private InvalidEventIdException(String message, String errorCode) {
        super(message, errorCode);
    }

    public static InvalidEventIdException nullEventId() {
        return new InvalidEventIdException(
                ErrorMessages.EVENT_ID_REQUIRED, ErrorCodes.INVALID_EVENT_ID);
    }
}
