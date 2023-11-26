package com.barbers.schedule.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    // @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ApiError> handleException(final RuntimeException ex, final WebRequest request) {
        ApiError apiError = ApiError.builder()
                .title("An internal error has occurred")
                .message(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    // @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<ApiError> handleIllegalArgumentException(final RuntimeException ex, final WebRequest request) {
        ApiError apiError = ApiError.builder()
                .title("An internal error has occurred")
                .message(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.NOT_FOUND.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }

}
