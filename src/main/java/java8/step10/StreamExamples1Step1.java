package java8.step10;

import java.util.stream.IntStream;

public class StreamExamples1Step1 {
    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> System.out.print(i + " "));
        System.out.println();
        IntStream.rangeClosed(1, 10).forEach(i -> System.out.print(i + " "));
    }
}
