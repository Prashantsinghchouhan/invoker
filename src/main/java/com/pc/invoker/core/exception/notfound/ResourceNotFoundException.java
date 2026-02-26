package com.pc.invoker.core.exception.notfound;

import com.pc.invoker.core.exception.InvokerException;

public abstract class ResourceNotFoundException extends InvokerException {
    protected ResourceNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }
}
