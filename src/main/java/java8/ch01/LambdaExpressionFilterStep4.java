package java8.ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class LambdaExpressionFilterStep4 {
    public static void main(String[] args) {
        /* Closure: 람다 바디에서 람다 바깥에 있는 factor (free variable) 접근
         * Note: 엄밀히 따지면 자바의 Closure는 variable이 아니라 거기 들은 값(value)에
         * 접근 하는겁니다 (capturing value).
         */
        int factor = 10; // effectively final
        final Comparator<Integer> comparator = (o1, o2) -> o1 > factor ? o1 : o1.compareTo(o2);
    }
}
