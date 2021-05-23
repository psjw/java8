package java8Stream;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples1 {
    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> System.out.print(i+" "));
        System.out.println();
        // 1 : seed 가장 첫값 / i -> i+1 : int를 받아서 int를 리턴
        IntStream.rangeClosed(1, 10).forEach(i -> System.out.print(i+" "));
//        IntStream.iterate(1, i -> i+1).forEach(i -> System.out.println(i+ " "));
//        Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE)).forEach(i -> System.out.print(i+" "));
    }
}
