package com.pc.invoker.core.exception;

import lombok.Getter;

@Getter
public abstract class InvokerException extends RuntimeException {
    private final String errorCode;

    protected InvokerException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
