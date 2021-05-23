package java8.ch02;

import java.util.function.Function;

public class FunctionExampleAnonymous {
    public static void main(String[] args) {
        Function<String, Integer> toInt = new Function<String, Integer>() {
            @Override
            public Integer apply(String value) {
                return Integer.parseInt(value);
            }
        };
        final Integer number = toInt.apply("100");
        System.out.println(number);

        final Function<Integer, Integer> identity2 = t -> t;
        System.out.println(identity2.apply(999));
    }
}
