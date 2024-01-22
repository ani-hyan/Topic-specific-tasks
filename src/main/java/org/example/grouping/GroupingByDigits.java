package org.example.grouping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByDigits {
    public Map<Integer, List<String>> groupByDigitNumbers(int[] input) {
        return  Arrays.stream(input)
                .boxed()
                .filter(x -> x > 0)
                .collect(Collectors.groupingBy(this::numberOfDigits))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        value -> value.getValue()
                                .stream()
                                .map(x -> evenOrOdd(x) + x).collect(Collectors.toList())));
    }

    private int numberOfDigits(int number){
        int count = 0;
        while(number > 0){
            number /= 10;
            count++;
        }
        return count;
    }

    private String evenOrOdd(int number){
        return number % 2 == 0 ? "e" : "o";
    }

}
