package com.tech.common.exception;

import com.tech.common.interfaces.IExceptionMessage;
import org.springframework.http.HttpStatus;

public class TechException extends RuntimeException{

    private final HttpStatus httpStatus;

    public TechException(IExceptionMessage exceptionMessage) {
        super( exceptionMessage.getMessage() );
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public TechException(String message, HttpStatus httpStatus) {
        super( message );
        this.httpStatus = httpStatus;
    }

    public TechException(IExceptionMessage exceptionMessage, HttpStatus httpStatus) {
        super( exceptionMessage.getMessage() );
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}