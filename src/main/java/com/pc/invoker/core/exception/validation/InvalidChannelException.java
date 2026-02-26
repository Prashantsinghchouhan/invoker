package com.pc.invoker.core.exception.validation;

import com.pc.invoker.core.exception.ErrorCodes;
import com.pc.invoker.core.exception.ErrorMessages;

public class InvalidChannelException extends ValidationException {

    private InvalidChannelException(String message, String errorCode) {
        super(message, errorCode);
    }

    public static InvalidChannelException nullChannel() {
        return new InvalidChannelException(
                ErrorMessages.CHANNEL_REQUIRED, ErrorCodes.INVALID_CHANNEL);
    }
}
