package com.example.webflux.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AlgorithmTest {

    @Test
    public void map() {
        int n = 6;
        double res = Math.sqrt(2 * Math.PI * n) + Math.pow(n / Math.E, n);
        System.out.println(res);
    }

}
