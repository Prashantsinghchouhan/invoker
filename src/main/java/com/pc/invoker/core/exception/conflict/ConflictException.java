package com.pc.invoker.core.exception.conflict;

import com.pc.invoker.core.exception.InvokerException;

public abstract class ConflictException extends InvokerException {
    protected ConflictException(String message, String errorCode) {
        super(message, errorCode);
    }
}
