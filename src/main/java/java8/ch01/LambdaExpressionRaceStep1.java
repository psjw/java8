package java8.ch01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class LambdaExpressionRaceStep1 {
    public static void main(String[] args) {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        final int repeat = 5;
        System.out.println("\nLambdaExample.raceCondition");
        /* 5번 반복 */
        Stream.iterate(0, i -> i + 1)
                .limit(repeat)
                .forEach(i -> raceConditionStep1(numbers));
    }
    private static void raceConditionStep1(final List<Integer> numbers) {
        /* Race condition */
        final int[] sum = new int[1];
        numbers.parallelStream()
                .forEach(i -> sum[0] = sum[0] + i); // mutation!

        System.out.println("race condition: " + sum[0]);
    }
}
