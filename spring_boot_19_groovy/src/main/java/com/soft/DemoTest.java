package com.soft;


import com.google.common.collect.Lists;
import groovy.lang.GroovyShell;

import java.util.ArrayList;

public class DemoTest {

    public static void main(String[] args) {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        GroovyShell groovyShell = new GroovyShell();
        groovyShell.evaluate("import integers;integers.stream().forEach {m ->println m}");
    }
}
