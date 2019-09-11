package com.example.webflux.dao;

import com.example.webflux.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserDao {

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    @Autowired
    public UserDao(ReactiveRedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public <S extends User> Mono<Long> save(S s) {
        return redisTemplate.opsForValue().append(s.getUsername(), String.valueOf(s));
    }

    public Mono<String> findById(String s) {
        return redisTemplate.opsForValue().get(s);
    }

    public Mono<Boolean> deleteById(String s) {
        return redisTemplate.opsForValue().delete(s);
    }

    public Mono<Long> deleteAll() {
        return redisTemplate.delete("*");
    }

}
