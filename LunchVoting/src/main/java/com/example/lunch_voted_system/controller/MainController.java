package com.example.lunch_voted_system.controller;

import com.example.lunch_voted_system.model.Lunch;
import com.example.lunch_voted_system.model.User;
import com.example.lunch_voted_system.repository.LunchRepository;
import com.example.lunch_voted_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private LunchRepository lunchService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String getLunchTable(Model model) {
        List<Lunch> lunches = lunchService.getAllLunches();
        model.addAttribute("lunches", lunches);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("email", email);
        return "index";
    }

    @GetMapping("/admin")
    public String getAdmin(Model model) {
        List<Lunch> lunches = lunchService.getAllLunches();
        model.addAttribute("lunches", lunches);
        List<User> users = userRepository.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }
}