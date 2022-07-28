package com.soft;

import java.util.HashMap;

public class HashMapTest {

    /**
     * 向HashMap中写入数据的过程，简单总结起来分为这么几步
     *
     * @param args
     */
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        String putValue = hashMap.put("1", "2");
        String oldValue = hashMap.put("1", "3");
        System.out.println(oldValue);


    }

}
