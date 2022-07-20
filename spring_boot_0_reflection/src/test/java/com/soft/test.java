package com.soft;

import cn.hutool.core.lang.Console;

import java.lang.reflect.Method;
import java.util.HashMap;

public class test {

    public static void main(String[] args) {
        HashMap<String, Class> map = new HashMap<>();
        map.put(DemoObject.class.getName(),DemoObject.class);
        Class aClass = map.get(DemoObject.class.getName());
        Method method = aClass.getMethod("getNameStr");
        Object invoke = method.invoke(aClass.newInstance());
        Console.log(invoke);
        Console.log(map);
    }

}
