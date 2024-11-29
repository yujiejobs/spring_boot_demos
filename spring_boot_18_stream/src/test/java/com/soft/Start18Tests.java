package com.soft;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;

//@SpringBootTest
class Start18Tests {

    @Test
    void contextLoads() {
        ArrayList<Integer> list = Lists.newArrayList(5, 3, 4, 7, 8, 22, 9, 0);
        // [0, 3, 4, 5, 7, 8, 9, 22]
        System.out.println(list.stream().sorted().collect(Collectors.toList()));
    }

    @Test
    void parallel() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10); 
        int sum = numbers.parallelStream() .mapToInt(Integer::intValue) .sum();
    }

}
