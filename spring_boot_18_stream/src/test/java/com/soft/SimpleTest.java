package com.soft;

import java.util.stream.Stream;

/**
 * 描述: SimpleTest
 *
 * @author yujie
 * @date 2022/11/27 16:44
 */
public class SimpleTest {

    public static void main(String[] args) {

        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, (sum, item) -> sum + item));

    }

}
