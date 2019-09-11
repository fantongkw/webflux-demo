package com.example.webflux.controller;

import com.example.webflux.model.User;
import com.example.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find/{id}")
    public Mono<String> findById(@PathVariable("id") String id) {
        return userService.findById(id);
    }

    @PostMapping("/save")
    public Mono<Long> save(User user) {
        return userService.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Boolean> deleteById(@PathVariable("id") String id) {
        return userService.deleteById(id);
    }
}
