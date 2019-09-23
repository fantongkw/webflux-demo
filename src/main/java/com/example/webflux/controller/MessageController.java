package com.example.webflux.controller;

import com.example.webflux.model.Message;
import com.example.webflux.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/find/all")
    public Flux<String> findAll() {
        return messageService.findAll();
    }

    @PostMapping("/save")
    public Mono<Void> save(Message message) {
        return messageService.save(message).then();
    }
}
