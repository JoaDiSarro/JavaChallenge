package com.example.demo.error.handler;

import com.example.demo.error.exception.MainException;
import com.example.demo.error.exception.PriceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            PriceNotFoundException.class
    })
    public ResponseEntity<Object> handleNotFoundException(final MainException ex, final WebRequest request) {
        final Map<String, String> responseBody = createExceptionResponseBody(ex);
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private Map<String, String> createExceptionResponseBody(final Exception exception) {
        String errorMessage;

        if (exception instanceof MainException && ((MainException) exception).getErrorMessage() != null) {
            errorMessage = ((MainException) exception).getErrorMessage();
        } else {
            errorMessage = "Unknown error";
        }
        return Collections.singletonMap("errorMessage", errorMessage);
    }
}