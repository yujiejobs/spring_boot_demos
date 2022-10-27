package com.soft;

import lombok.Data;

@Data
public class Demo {

    private String name;

    private Demo demoInner;

    public Demo() {
    }

    public Demo(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

    }
}
