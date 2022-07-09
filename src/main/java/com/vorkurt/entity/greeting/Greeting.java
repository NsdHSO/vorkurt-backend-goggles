package com.vorkurt.entity.greeting;

import com.vorkurt.entity.greeting.request.GreetingRequest;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name= "greeting")
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="message")
    private String message;

    @Column(name="messageFromWho")
    private String fromWho;

    public Greeting(GreetingRequest greeting){
        this.message = greeting.getMessage();
        this.fromWho = greeting.getMessageFromWho();
    }

}
