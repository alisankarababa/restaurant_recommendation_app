package com.tech.user_service.controller_advice;

import com.tech.common.dto.ExceptionDto;
import com.tech.common.exception.TechException;
import com.tech.common.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

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
    ResponseEntity<RestResponse<ExceptionDto>> hMethodArgumentNotValidException(MethodArgumentNotValidException exc, WebRequest webRequest) {
        ExceptionDto exceptionDTO = getExceptionDto(exc, webRequest);
        return new ResponseEntity<>(RestResponse.error(exceptionDTO), HttpStatus.BAD_REQUEST);
    }

    private static ExceptionDto getExceptionDto(Exception exc, WebRequest webRequest) {
        String details = webRequest.getDescription(false);
        String message = exc.getMessage();
        return new ExceptionDto(message, details);
    }
}