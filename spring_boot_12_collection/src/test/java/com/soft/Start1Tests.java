package com.soft;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Start1Tests {

    @Test
    void contextLoads() {

        int i = 1;
        System.out.println(1%32);
        System.out.println(1/32%16);
    }

    public static void main(String[] args) {
        int i = 1;
        System.out.println(1%32);
        System.out.println(1/32%16);
    }

}
