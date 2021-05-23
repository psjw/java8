package java8.ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaExpressionFilterStep3 {
    public static void main(String[] args) {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        /*
            Function composition
            2개의 함수를 합쳐서 쉽게 2보다 크고 7보다 작은 정수 찾기
         */
        Predicate<Integer> lessThan7 = i -> i < 7;
        Predicate<Integer> greaterThan2 = i -> i > 2;
        final List<Integer> result = filterStep3(list, greaterThan2.and(lessThan7));
        System.out.println("2 < n < 7 = " + result);
    }

    private static <T> List<T> filterStep3(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T value : list) {
            if (predicate.test(value)) {
                result.add(value);
            }
        }
        return result;
    }
}
