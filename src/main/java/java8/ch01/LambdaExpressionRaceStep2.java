package java8.ch01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class LambdaExpressionRaceStep2 {
    public static void main(String[] args) {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        final int repeat = 5;
        System.out.println("\nLambdaExample.raceCondition");
        /* 5번 반복 */
        Stream.iterate(0, i -> i + 1)
                .limit(repeat)
                .forEach(i -> noRaceConditionStep2(numbers));
    }
    private static void noRaceConditionStep2(final List<Integer> numbers) {
        /* No race condition */
        final int total = numbers.parallelStream()
                .reduce(0, (i1, i2) -> i1 + i2);
        System.out.println("no race condition: " + total);
    }
}
