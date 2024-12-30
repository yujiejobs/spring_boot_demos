package com.soft.guava.list;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoStartTest {

    public static void main(String[] args) {
        ArrayList<String> list = Lists.newArrayList("1,2,3,4,5".split(","));
        list.add("6");

        // Exception in thread "main" java.lang.UnsupportedOperationException
        List<String> list1 = Arrays.asList("1,2,3,4,5".split(","));
        list1.add("6");
    }
}
