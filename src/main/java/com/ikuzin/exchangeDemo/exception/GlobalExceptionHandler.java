package com.ikuzin.exchangeDemo.exception;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String WRONG_WITH_API = "Something wrong with external API";
    private static final String INVALID_ARGUMENTS = "Invalid arguments passed as arguments";

    private ResponseEntity<Object> baseHandle(String forOut, Exception exception, HttpStatus status, WebRequest request) {
        log.error(exception.getLocalizedMessage());
        return handleExceptionInternal(exception, forOut, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleInvalidData(Exception exception, WebRequest request) {
        return baseHandle(INVALID_ARGUMENTS, exception, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({FeignException.class, CallingExternalAPIException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
        return baseHandle(WRONG_WITH_API, exception, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
