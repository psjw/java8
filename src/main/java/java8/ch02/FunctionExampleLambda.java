package java8.ch02;

import java.util.function.Function;

public class FunctionExampleLambda {
    public static void main(String[] args) {
        final Function<String, Integer> toInt = value -> Integer.parseInt(value);
        final Integer number = toInt.apply("100");
        System.out.println(number);

        final Function<Integer, Integer> identity = Function.identity();
        System.out.println(identity.apply(999));
    }
}
