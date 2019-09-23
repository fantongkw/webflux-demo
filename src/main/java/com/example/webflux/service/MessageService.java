package com.example.webflux.service;

import com.example.webflux.dao.MessageDao;
import com.example.webflux.model.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

    private final MessageDao messageDao;

    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public Mono<Long> save(Message message) {
        return messageDao.save(message);
    }

    public Flux<String> findAll() {
        return messageDao.findAll();
    }

    public Mono<String> findById(String s) {
        return messageDao.findById(s);
    }

    public Mono<Boolean> deleteById(String s) {
        return messageDao.deleteById(s);
    }

    public Mono<Long> deleteAll() {
        return messageDao.deleteAll();
    }
}
