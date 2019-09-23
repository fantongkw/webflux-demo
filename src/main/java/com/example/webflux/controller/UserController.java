package com.example.webflux.controller;

import com.example.webflux.model.User;
import com.example.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find/{id}")
    public Mono<String> findById(@PathVariable("id") String id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/find/all", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<String> findAll() {
        return userService.findAll().delayElements(Duration.ofSeconds(1));
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
