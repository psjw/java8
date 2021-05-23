package java8.ch01;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaExpressionFilterStep2 {

    public static void main(final String[] args) {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //람다를 이용해서 2보다 큰 정수 찾기
        final Predicate<Integer> greaterThan2 = n -> n > 2;
        final List<Integer> result = filterStep2(list, greaterThan2);
        System.out.println("n > 2 = "+result);

        //람다를 이용해서 7보다 작은 정수 찾기
        final Predicate<Integer> lessThan7 = n -> n < 7;
        final List<Integer> result1 = filterStep2(list, lessThan7);
        System.out.println("n < 7 = "+result1);
    }

    private static <T> List<T> filterStep2(final List<T> list, final Predicate<T> predicate) {
        final List<T> result = new ArrayList<>();
        for (final T value : list) {
            if(predicate.test(value)){
                result.add(value);
            }
        }
        return result;
    }
}


