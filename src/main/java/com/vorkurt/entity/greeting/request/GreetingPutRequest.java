package com.vorkurt.entity.greeting.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GreetingPutRequest {

    @NotNull(message = "Greeting Id Is required")
    private long id;
    private String message;
    private String messageFromWho;
}
