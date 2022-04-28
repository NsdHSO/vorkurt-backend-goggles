package com.vorkurt.controller.greeting;

import com.vorkurt.entity.greeting.request.GreetingPutRequest;
import com.vorkurt.entity.greeting.response.GreetingResponse;
import com.vorkurt.service.greeting.GreetingService;
import com.vorkurt.entity.greeting.Greeting;
import com.vorkurt.entity.greeting.request.GreetingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/greeting")
public class GreetingController {

    @Autowired
    GreetingService greetingService;

    @GetMapping()
    public List<GreetingResponse> getAllGreeting(){
        List<Greeting> allGrettingFromDB = greetingService.getAllGreeting();
        List<GreetingResponse> gretings = new ArrayList<GreetingResponse>();

        allGrettingFromDB.stream().forEach(greeting -> {
            gretings.add(new GreetingResponse(greeting));
        });

        return gretings;
    }

    @PostMapping()
    public Greeting createGreeting(@Valid @RequestBody GreetingRequest greetingRequest){
        Greeting greeting = this.greetingService.createGreeting(greetingRequest);
        return greeting;
    }

    @PutMapping()
    public Greeting updateGreeting(@Valid @RequestBody GreetingPutRequest greetingPutRequest){
        Greeting greeting = this.greetingService.updateGreeting(greetingPutRequest);
        return greeting;
    }

    @GetMapping("{message}")
    public List<GreetingResponse> gettingAllMessageWith(@PathVariable String message){
        List<Greeting> allGrettingFromDB = greetingService.getAllMessages(message);
        List<GreetingResponse> messagesWith = new ArrayList<>();

        allGrettingFromDB.stream().forEach(greeting -> {
            messagesWith.add(new GreetingResponse(greeting));
        });
        return messagesWith;

    }

    @GetMapping("{message}/{fromWho}")
    public Greeting gettinAllMessageAndFromWho(@PathVariable String message,@PathVariable String fromWho){
        return greetingService.getMessage(message, fromWho);
    }

    @DeleteMapping()
    public  String deleteGreeting(@RequestParam long id ){
        return this.greetingService.delete(id);
    }


}
