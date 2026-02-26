package com.pc.invoker.core.exception.validation;

import com.pc.invoker.core.exception.InvokerException;

public abstract class ValidationException extends InvokerException {
    protected ValidationException(String message, String errorCode) {
        super(message, errorCode);
    }
}
