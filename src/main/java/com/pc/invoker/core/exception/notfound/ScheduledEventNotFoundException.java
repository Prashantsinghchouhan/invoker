package com.pc.invoker.core.exception.notfound;

import com.pc.invoker.core.exception.ErrorCodes;
import com.pc.invoker.core.exception.ErrorMessages;

public class ScheduledEventNotFoundException extends ResourceNotFoundException {

    private ScheduledEventNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public static ScheduledEventNotFoundException byId(String id) {
        return new ScheduledEventNotFoundException(
                String.format(ErrorMessages.EVENT_NOT_FOUND, id), ErrorCodes.EVENT_NOT_FOUND);
    }
}
