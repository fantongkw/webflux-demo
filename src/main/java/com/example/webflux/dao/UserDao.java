package com.example.webflux.dao;

import com.example.webflux.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserDao {
    private final String namespace = "user:";

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    public UserDao(ReactiveRedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public <S extends User> Mono<Long> save(S s) {
        return redisTemplate.opsForValue().append(getUserKey(s.getName()), String.valueOf(s));
    }

    public Flux<String> findAll() {
        return redisTemplate.keys(getUserKey("*"));
    }

    public Mono<String> findById(String s) {
        return redisTemplate.opsForValue().get(getUserKey(s));
    }

    public Mono<Boolean> deleteById(String s) {
        return redisTemplate.opsForValue().delete(getUserKey(s));
    }

    public Mono<Long> deleteAll() {
        Flux<String> keys = redisTemplate.keys(getUserKey("*"));
        return redisTemplate.delete(keys);
    }

    private String getUserKey(String key) {
        return this.namespace + key;
    }

}
