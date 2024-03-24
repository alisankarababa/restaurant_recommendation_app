package com.tech.user_service.exception;

import com.tech.common.exception.TechException;
import com.tech.user_service.entity.BaseEntity;
import org.springframework.http.HttpStatus;

public class BaseEntityNotFoundException extends TechException {

    public BaseEntityNotFoundException(Class<? extends BaseEntity> classEntity) {
        super( classEntity.getName()  + " not found.", HttpStatus.NOT_FOUND );
    }
}