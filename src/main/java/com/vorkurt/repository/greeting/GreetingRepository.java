package com.vorkurt.repository.greeting;

import com.vorkurt.entity.greeting.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
       List<Greeting> findByMessage(String message);
       Greeting findByMessageAndFromWho(String message, String messageFromWho);
}
