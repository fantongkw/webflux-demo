package com.example.webflux.dao;

import com.example.webflux.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MessageDao {

    private final String namespace = "message:";

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    public MessageDao(ReactiveRedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public <S extends Message> Mono<Long> save(S s) {
        return redisTemplate.opsForValue().append(getMessageKey(s.getId()), String.valueOf(s));
    }

    public Flux<String> findAll() {
        return redisTemplate.keys(getMessageKey("*"));
    }

    public Mono<String> findById(String s) {
        return redisTemplate.opsForValue().get(getMessageKey(s));
    }

    public Mono<Boolean> deleteById(String s) {
        return redisTemplate.opsForValue().delete(getMessageKey(s));
    }

    public Mono<Long> deleteAll() {
        Flux<String> keys = redisTemplate.keys(getMessageKey("*"));
        return redisTemplate.delete(keys);
    }

    private String getMessageKey(String key) {
        return this.namespace + key;
    }
}
