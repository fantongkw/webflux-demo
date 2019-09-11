package com.example.webflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("user")
public class User {
    @Id
    private String id;
    private String username;
    private String phone;
    private String email;
    private String name;
    private Calendar birthday;
}
