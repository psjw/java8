package java8Tube;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamExamples5ParallelPerformance {

    private static void slowDown() {
        try {
            TimeUnit.MILLISECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //1~n까지 더함
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
            slowDown();
        }
        return result;
    }

    //Stream.iterate(1L, i -> i + 1)  1,2,3... (무한증가)
    //.limit(n) n까지만 생성
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).peek(i -> slowDown()).reduce(Long::sum).get();
    }

    //하나처리될떄까지 병렬 Thread가 기다림 체크하기때문에 오히려 느림
    //peek 는 스트림을 리턴 -> 리턴값이 없음
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().peek(i -> slowDown()).reduce(Long::sum).get();
    }
    
    //rangeClosed(1, n)  1~n까지 증가
    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).peek(i -> slowDown()).reduce(Long::sum).getAsLong();
    }

    //단순 계산은 느림
    //중간 단계에  map , filter Intermeiate Operation Function이 있을경우에 속도가 빠름
    //Stream -> 1초 * n
    //Parallel -> 1초 *(n/core)
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().peek(i -> slowDown()).reduce(Long::sum).getAsLong();
    }

    public static void main(String[] args) {
        final long n = 1000;
        final long start = System.currentTimeMillis();
//    1 + 2 + 3 + ... + 98 + 99 + 100
        System.out.println((1 + n) * (n / 2));
        System.out.println("           Gauss Way: " + (System.currentTimeMillis() - start));

        final long start1 = System.currentTimeMillis();
        System.out.println("     iterativeSum(n): " + iterativeSum(n));
        System.out.println("                      " + (System.currentTimeMillis() - start1) + " ms\n");
        final long start2 = System.currentTimeMillis();
        System.out.println("    sequentialSum(n): " + sequentialSum(n));
        System.out.println("                      " + (System.currentTimeMillis() - start2) + " ms\n");
        final long start3 = System.currentTimeMillis();
        System.out.println("      parallelSum(n): " + parallelSum(n));
        System.out.println("                      " + (System.currentTimeMillis() - start3) + " ms\n");
        final long start4 = System.currentTimeMillis();
        System.out.println("        rangedSum(n): " + rangedSum(n));
        System.out.println("                      " + (System.currentTimeMillis() - start4) + " ms\n");
        final long start5 = System.currentTimeMillis();
        System.out.println("parallelRangedSum(n): " + parallelRangedSum(n));
        System.out.println("                      " + (System.currentTimeMillis() - start5) + " ms\n");
    }
}
