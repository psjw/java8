package java8.ch01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WhyJava8ListStep3 {
    static final List<Integer> numbers = Arrays.asList(1, 2, 4, 5, 6, 7, 8, 9, 10);

    public static void main(String[] args) {
        //Java 8
        final String result = numbers.stream()
                .map(String::valueOf)
                //.map(i -> String.valueOf(i))
                .collect(Collectors.joining(" : "));
        System.out.println(result);

    }
}
