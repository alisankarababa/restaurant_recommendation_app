package com.tech.common.exception;

import com.tech.common.interfaces.IExceptionMessage;
import com.tech.common.enums.eTechExceptionMessage;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends TechException{

    public ResourceNotFoundException() {
        super( eTechExceptionMessage.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND );
    }

    public ResourceNotFoundException(IExceptionMessage exceptionMessage) {
        super( exceptionMessage, HttpStatus.NOT_FOUND );
    }
}
