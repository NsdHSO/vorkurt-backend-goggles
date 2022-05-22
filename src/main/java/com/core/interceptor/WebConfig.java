package com.core.interceptor;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    RetrieveDataFromUserInterceptor retrieveDataFromUserInterceptor;

    @Autowired
    private Request requestInterceptor;

    Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);

    WebConfig(){
        LOGGER.debug(WebConfig.class + " Init");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(requestInterceptor);

        registry.addInterceptor(retrieveDataFromUserInterceptor);
        // next two should be avoid -- tightly coupled and not very testable
        registry.addInterceptor(new RetrieveDataFromUserInterceptor());
        registry.addInterceptor(new HandlerInterceptor() {
        });
    }
}
