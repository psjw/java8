package java8.ch03;

import java.util.function.Consumer;

public class ConsumerExamplesAnonymous {
    public static void main(String[] args) {
        final Consumer<String> print = new Consumer<String>() {
            @Override
            public void accept(String value) {
                System.out.println(value);
            }
        };
        print.accept("Hello");

        final Consumer<String> greetings = new Consumer<String>() {
            @Override
            public void accept(String value) {
                System.out.println("Hello " + value);
            }
        };
        greetings.accept("World");
    }
}
