package com.core.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RetrieveDataFromUserInterceptor implements HandlerInterceptor {
    Logger LOGGER = LoggerFactory.getLogger(RetrieveDataFromUserInterceptor.class);

    public RetrieveDataFromUserInterceptor(){
        LOGGER.debug(RetrieveDataFromUserInterceptor.class + "  init");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.warn("Pre Handle ");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.warn("Post Handle ");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.warn("After Handle ");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
