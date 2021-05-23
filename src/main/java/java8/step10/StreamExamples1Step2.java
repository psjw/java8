package java8.step10;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples1Step2 {
    public static void main(String[] args) {
        IntStream.iterate(1, i -> i + 1)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
        Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE))
                .forEach(i -> System.out.print(i + " "));
    }
}
