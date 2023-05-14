package com.example.lunch_voted_system;

import com.example.lunch_voted_system.repository.LunchRepository;
import com.example.lunch_voted_system.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor

public class LunchVotedSystemApplication {

    private UserRepository userRepository;
    private LunchRepository lunchRepository;

    public static void main(String[] args) {
        SpringApplication.run(LunchVotedSystemApplication.class, args);
    }

}
