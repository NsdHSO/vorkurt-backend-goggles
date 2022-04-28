package com.vorkurt.service.greeting;

import com.vorkurt.entity.greeting.request.GreetingPutRequest;
import com.vorkurt.entity.greeting.Greeting;
import com.vorkurt.entity.greeting.request.GreetingRequest;
import com.vorkurt.repository.greeting.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {

    @Autowired
    GreetingRepository greetingRepository;

    public List<Greeting> getAllGreeting(){
        return greetingRepository.findAll();
    }

    public Greeting createGreeting(GreetingRequest createReq){
        Greeting greeting = new Greeting(createReq);
        return this.greetingRepository.save(greeting);
    }

    public Greeting updateGreeting (GreetingPutRequest greetingPutRequest){
        Greeting greeting = greetingRepository.findById(greetingPutRequest.getId()).get();

        if(greetingPutRequest.getMessage() != null){
            greeting.setMessage(greetingPutRequest.getMessage());
        }
        if( greetingPutRequest.getMessageFromWho() != null){
            greeting.setFromWho(greetingPutRequest.getMessageFromWho());
        }
        this.greetingRepository.save(greeting);
        return greeting;
    }

    public List<Greeting> getAllMessages(String messagesContains){
        return this.greetingRepository.findByMessage(messagesContains);
    }

    public Greeting getMessage(String message, String fromWho){
        return this.greetingRepository.findByMessageAndFromWho(message, fromWho);
    }

    public String delete (long id){
        greetingRepository.deleteById(id);
        return "Delete Greeting Successful";
    }
}
