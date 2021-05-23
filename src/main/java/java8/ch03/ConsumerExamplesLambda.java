package java8.ch03;

import java.util.function.Consumer;

public class ConsumerExamplesLambda {
    public static void main(String[] args) {
        final Consumer<String> print = value -> System.out.println(value);
        print.accept("Hello");

        final Consumer<String> greetings = value -> System.out.println("Hello " + value);
        greetings.accept("World");
    }
}
