package com.vorkurt.controller;

import com.core.exception.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionServerHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionServerHandler.class);
    @Autowired
    private ApplicationContext appContext;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> dataConenction(Exception ex, WebRequest request) {
        return new ResponseEntity<ExceptionResponse>(new ExceptionResponse.ExceptionBuilder(HttpStatus.INTERNAL_SERVER_ERROR,
                "You introduce filed what not exists in DAO").title("Exception").instancePath(request.getContextPath()).build(),
                HttpStatus.OK);
    }

}
