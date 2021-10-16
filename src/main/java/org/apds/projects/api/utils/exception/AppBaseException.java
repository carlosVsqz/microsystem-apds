package org.apds.projects.api.utils.exception;

import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

import lombok.Getter;

@Getter
public class AppBaseException extends RuntimeException implements Supplier<RuntimeException> {

    private final HttpStatus status;

    private final ErrorCode errorCode;

    private final Object[] args;

    public AppBaseException(HttpStatus status, ErrorCode code) {
        this(HttpStatus.BAD_REQUEST, code, null);
    }

    public AppBaseException(HttpStatus status, ErrorCode code, Object[] msgArgs) {
        super();
        this.status = status;
        this.errorCode = code;
        this.args = msgArgs;
    }

    public AppBaseException(HttpStatus status, ErrorCode code, Object[] msgArgs, Throwable cause) {
        this(status, code, msgArgs, cause.getLocalizedMessage(), cause);
    }

    public AppBaseException(HttpStatus status, ErrorCode code, Object[] msgArgs,  String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.errorCode = code;
        this.args = msgArgs;
    }

    @Override
    public RuntimeException get() {
        return this;
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }
}
