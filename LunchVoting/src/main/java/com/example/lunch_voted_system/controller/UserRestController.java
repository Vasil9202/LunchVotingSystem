package com.example.lunch_voted_system.controller;

import com.example.lunch_voted_system.AuthUser;
import com.example.lunch_voted_system.model.User;
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
public class UserRestController {

    private UserRepository userRepository;

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Integer id, @AuthenticationPrincipal AuthUser authUser) {
        // Check if the authenticated user has the necessary role
        // Delete the user with the specified id
        userRepository.deleteById(id);
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable("id") Integer id, @Valid @RequestBody User user,
                           @AuthenticationPrincipal AuthUser authUser) {
        log.info("update {} to {}", authUser, user);
        userRepository.save(user);
    }

    @PostMapping("/users/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveUser(@Valid @RequestBody User user,
                           @AuthenticationPrincipal AuthUser authUser) {
        User newUser = new User(user.getEmail(),user.getFirstName(),user.getLastName(), user.getPassword(), user.getRoles(),"");
        log.info("update {} to {}", authUser, user);
        userRepository.save(newUser);
    }
}