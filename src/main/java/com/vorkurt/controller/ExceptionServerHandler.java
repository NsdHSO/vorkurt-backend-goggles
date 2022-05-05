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
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<ExceptionResponse> dataConenction(Exception ex, WebRequest request, HandlerMethod handlerMethod, HttpServletRequest requestPath) {
        LOGGER.warn("Exception Handle " + handlerMethod.getMethod().getDeclaringClass());
        return new ResponseEntity<ExceptionResponse>(new ExceptionResponse.ExceptionBuilder(HttpStatus.INTERNAL_SERVER_ERROR,
                "You entered a field that is not good for this case. :-) be carefull ").title("Exception")
                .instancePath(request.getContextPath())
                .URIType(handlerMethod.getMethod().getName()).instancePath(requestPath.getRequestURL().toString()).build(),
                HttpStatus.OK);
    }

}
