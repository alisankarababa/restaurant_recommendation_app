package com.tech.user_service.exception;

import com.tech.common.exception.ResourceNotFoundException;
import com.tech.user_service.enums.eConcreteBaseEntityExceptionMessage;

public class ConcreteBaseEntityNotFoundException extends ResourceNotFoundException {

    public ConcreteBaseEntityNotFoundException() {
        super( eConcreteBaseEntityExceptionMessage.NOT_FOUND );
    }

}
