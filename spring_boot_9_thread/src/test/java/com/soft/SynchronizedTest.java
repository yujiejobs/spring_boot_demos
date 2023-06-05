package com.soft;

public class SynchronizedTest {

    public static void main(String[] args) {
        new SynchronizedTest().runTest();
    }

    private void runTest() {
        int a = 1;
        synchronized (this) {
            System.out.printf(String.valueOf(a));
        }
    }
}
