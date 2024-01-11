package org.example;

import org.example.grouping.GroupingByDigits;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] input = new int[]{3,44,108, 8, 51};
        Map<Integer, List<String>> arr = new GroupingByDigits().groupByDigitNumbers(input);

        arr.entrySet().forEach(System.out::println);

    }
}