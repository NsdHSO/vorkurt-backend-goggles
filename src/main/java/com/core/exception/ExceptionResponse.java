package com.core.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionResponse {
    private String method;
    private String title;
    private HttpStatus status;
    private String detail;
    private String instancePath;

    private ExceptionResponse(ExceptionBuilder excetionBuilder){
        this.title = excetionBuilder.title;
        this.status = excetionBuilder.httpStatus;
        this.detail = excetionBuilder.detail;
        this.instancePath = excetionBuilder.instancePath;
        this.method = excetionBuilder.type;

    }

    public static class ExceptionBuilder{
        private String type;
        private HttpStatus httpStatus;
        private String detail;
        private String title;
        private String instancePath;

        public ExceptionBuilder(){
            this.type = null;
            this.httpStatus = null;
        }


        public ExceptionBuilder(HttpStatus httpStatus, String detail){
            this.httpStatus = httpStatus;
            this.detail = detail;
        }

        public ExceptionBuilder title(String title){
            this.title = title;
            return this;
        }

        public ExceptionBuilder instancePath(String instancePath){
            this.instancePath = instancePath;
            return this;
        }

        public ExceptionResponse build(){
            return new ExceptionResponse(this);
        }
        public ExceptionBuilder URIType(String URIType){
            this.type = URIType;
            return this;
        }
    }
}
