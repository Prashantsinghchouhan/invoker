package com.pc.invoker.adapter.in.kafka;

import com.pc.invoker.core.exception.InvokerException;
import com.pc.invoker.core.exception.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaErrorHandler implements KafkaListenerErrorHandler {

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException ex) {
        Throwable cause = ex.getCause();

        if (cause instanceof ValidationException ve) {
            log.error("Validation failed for Kafka message: code={}, detail={}, payload={}",
                    ve.getErrorCode(), ve.getMessage(), message.getPayload());
            return null;
        }

        if (cause instanceof InvokerException ie) {
            log.error("Domain error processing Kafka message: code={}, detail={}",
                    ie.getErrorCode(), ie.getMessage());
            return null;
        }

        log.error("Unexpected error processing Kafka message: payload={}",
                message.getPayload(), cause);
        throw ex;
    }
}
