package com.example.webflux.service;

import com.example.webflux.dao.UserDao;
import com.example.webflux.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Mono<Long> save(User user) {
        return userDao.save(user)
                .onErrorResume(e ->
                        userDao.findById(user.getName())
                                .flatMap(originalUser -> {
                                    user.setId(originalUser);
                                    return userDao.save(user);
                                }));
    }

    public Mono<String> findById(String s) {
        return userDao.findById(s);
    }

    public Mono<Boolean> deleteById(String s) {
        return userDao.deleteById(s);
    }

    public Mono<Long> deleteAll() {
        return userDao.deleteAll();
    }
}
