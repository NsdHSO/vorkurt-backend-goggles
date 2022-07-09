package com.vorkurt.entity.greeting.response;

import com.vorkurt.entity.greeting.Greeting;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GreetingResponse {
    private String message;
    private String messageFrom;

    public GreetingResponse(Greeting greeting){
        this.message = greeting.getMessage();
        this.messageFrom = greeting.getFromWho();
    }

}
