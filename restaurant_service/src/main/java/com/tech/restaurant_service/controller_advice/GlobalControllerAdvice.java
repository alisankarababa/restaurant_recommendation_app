package com.tech.restaurant_service.controller_advice;

import com.tech.common.dto.ExceptionDto;
import com.tech.common.exception.TechException;
import com.tech.common.response.RestResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<RestResponse<ExceptionDto>> hException(Exception exception, WebRequest webRequest) {

        ExceptionDto exceptionDto = getExceptionDto(exception, webRequest);
        return new ResponseEntity<>(RestResponse.error(exceptionDto), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    ResponseEntity<RestResponse<ExceptionDto>> hTechException(TechException exc, WebRequest webRequest) {
        ExceptionDto exceptionDTO = getExceptionDto(exc, webRequest);
        return new ResponseEntity<>(RestResponse.error(exceptionDTO), exc.getHttpStatus());
    }

    @ExceptionHandler
    ResponseEntity<RestResponse<ExceptionDto>> hConstraintViolationException(ConstraintViolationException exc, WebRequest webRequest) {
        ExceptionDto exceptionDTO = getExceptionDto(exc, webRequest);
        return new ResponseEntity<>(RestResponse.error(exceptionDTO), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<RestResponse<ExceptionDto>> hMethodArgumentNotValidException(MethodArgumentNotValidException exc, WebRequest webRequest) {

        Map<String, String> errorMap = new HashMap<>();

        exc.getBindingResult().getFieldErrors().forEach( fieldError -> {

            String field = fieldError.getField();

            if(errorMap.containsKey( field )) {

                String message = errorMap.get( field );
                message += " " + fieldError.getDefaultMessage();
                errorMap.put( field, message );

            } else {
                errorMap.put( field, fieldError.getDefaultMessage() );
            }
        } );

        StringBuilder stringBuilder = new StringBuilder();
        for(var entry  : errorMap.entrySet()) {
            stringBuilder.append( entry.getKey() )
                    .append( ": " )
                    .append( entry.getValue() )
                    .append( ", " );
        }

        stringBuilder.deleteCharAt( stringBuilder.length() - 2 );
        stringBuilder.deleteCharAt( stringBuilder.length() - 1 );

        String description = webRequest.getDescription(false);

        return new ResponseEntity<>(RestResponse.error(new ExceptionDto( stringBuilder.toString(), description )), HttpStatus.BAD_REQUEST);
    }

    private static ExceptionDto getExceptionDto(Exception exc, WebRequest webRequest) {
        String details = webRequest.getDescription(false);
        String message = exc.getMessage();
        return new ExceptionDto(message, details);
    }
}