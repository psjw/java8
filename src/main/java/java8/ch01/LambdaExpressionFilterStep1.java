package java8.ch01;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaExpressionFilterStep1 {

    public static void main(final String[] args) {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 기존 방법으로 2보다 큰 정수 찾기
        final List<Integer> result = new ArrayList<>();
        for (final Integer number : list) {
            if (number > 2){
                result.add(number);
            }
        }
        System.out.println("n > 2 = " + result);

        // 기존 방법으로 7보다 작은 정수 찾기
        final List<Integer> result1 = new ArrayList<>();
        for (final Integer number : list) {
            if (number < 7){
                result1.add(number);
            }
        }
        System.out.println("n < 7 = " + result1);
    }
}
