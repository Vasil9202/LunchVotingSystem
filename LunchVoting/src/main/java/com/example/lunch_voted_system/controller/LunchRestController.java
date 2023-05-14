package com.example.lunch_voted_system.controller;

import com.example.lunch_voted_system.AuthUser;
import com.example.lunch_voted_system.model.Lunch;
import com.example.lunch_voted_system.repository.LunchRepository;
import com.example.lunch_voted_system.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class LunchRestController {

    private LunchRepository lunchRepository;

    private UserRepository userRepository;

    @PostMapping("/lunches/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveLunch(@Valid @RequestBody Lunch lunch,
                           @AuthenticationPrincipal AuthUser authUser) {
        Lunch newLunch = new Lunch(lunch.getRestaurantName(),lunch.getStarter(),lunch.getMainCourse(),
                lunch.getSalad(), lunch.getDessert(), lunch.getDrinks(),lunch.getVotes());
        log.info("save lunch {} to {}", authUser, lunch);
        lunchRepository.save(newLunch);
    }

    @PutMapping("/lunches/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLunch(@PathVariable("id") Integer id, @Valid @RequestBody Lunch lunch,
                           @AuthenticationPrincipal AuthUser authUser) {
        log.info("update lunch {} to {}", authUser, lunch);
        lunchRepository.save(lunch);
    }

    @DeleteMapping("/lunches/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLunch(@PathVariable("id") Integer id, @AuthenticationPrincipal AuthUser authUser) {
        lunchRepository.deleteById(id);
    }
}
