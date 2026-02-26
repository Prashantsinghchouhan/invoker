package com.pc.invoker.core.exception.validation;

import com.pc.invoker.core.exception.ErrorCodes;
import com.pc.invoker.core.exception.ErrorMessages;

public class InvalidPayloadException extends ValidationException {

    private InvalidPayloadException(String message, String errorCode) {
        super(message, errorCode);
    }

    public static InvalidPayloadException nullPayload() {
        return new InvalidPayloadException(
                ErrorMessages.PAYLOAD_REQUIRED, ErrorCodes.INVALID_PAYLOAD);
    }
}
