package com.example.lunch_voted_system.controller;

import com.example.lunch_voted_system.AuthUser;
import com.example.lunch_voted_system.model.Lunch;
import com.example.lunch_voted_system.model.User;
import com.example.lunch_voted_system.repository.LunchRepository;
import com.example.lunch_voted_system.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class VoteRestController {

    private LunchRepository lunchRepository;

    private UserRepository userRepository;

    @GetMapping("/vote/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@PathVariable("id") Integer id, @AuthenticationPrincipal AuthUser authUser) {
        System.out.println("!!!!!!!!!!!!!!");
        log.info("update lunch {} to {}", id, authUser.getUser());
        LocalTime currentTime = LocalTime.now();
        LocalTime thresholdTime = LocalTime.parse("11:00");
        if (currentTime.isAfter(thresholdTime) && !authUser.getUser().getVoted().isEmpty()) {
        }
        else if(!authUser.getUser().getVoted().isEmpty()) {
            String oldRestaurantName = authUser.getUser().getVoted();
            Lunch lunch = lunchRepository.findByRestaurantNameContainingIgnoreCase(oldRestaurantName).get();
            lunch.setVotes(lunch.getVotes() - 1);
            lunchRepository.save(lunch);
            String newRestaurantName = lunchRepository.findById(id).get().getRestaurantName();
            User user = authUser.getUser();
            user.setVoted(newRestaurantName);
            userRepository.save(user);
        }
        else {
            String newRestaurantName = lunchRepository.findById(id).get().getRestaurantName();
            User user = authUser.getUser();
            user.setVoted(newRestaurantName);
            userRepository.save(user);
            Lunch lunch = lunchRepository.findById(id).get();
            lunch.setVotes(lunch.getVotes() + 1);
            lunchRepository.save(lunch);
        }
    }

    @GetMapping("/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearVotes() {
            lunchRepository.clearVotes("0");
            userRepository.clearVotes("");
        }
}
