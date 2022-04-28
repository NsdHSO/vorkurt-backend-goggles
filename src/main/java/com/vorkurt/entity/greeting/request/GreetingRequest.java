package com.vorkurt.entity.greeting.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GreetingRequest {
    @JsonProperty("message")
    @NotBlank(message= "message is required")
    private String message;
    @JsonProperty("messageFrom")
    private String messageFromWho;
}
