package java8Stream;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples5Parallel {
    public static void main(String[] args) {

        //Anonymous Class(익명 클래스)의 경우 class외부의 variable에 접근하기 위해서는 그
        //variable이 final variable이여야만 합니다.
        //array의 경우 array자체가 Object이고  그안의 값 변경ㅇ은 array Object의 상태변경이라고 생각하면된다.
        final int[] sum = {0};
        //싱글코어
        IntStream.range(0, 100)
                .forEach(i -> sum[0] += i);
        System.out.println("sum : " + sum[0]);
        //병렬프로그래밍
        //값이 다르게 나와야하는데 제대로 나오는데 자바 버전차이인가봄
        final int[] sum2 = {0};
        IntStream.range(0, 100)
                .parallel()
                .forEach(i -> sum2[0] += i);
        System.out.println("sum2 (with side-effect) : " + sum2[0]);

        System.out.println("stream sum (no side-effect) : " +
                IntStream.range(0, 100)
                        .sum()
        );

        System.out.println("parallel stream sum (no side-effect) : " +
                IntStream.range(0, 100)
                        .parallel()
                        .sum()
        );
        System.out.println("=======================================");
        System.out.println("Stream");
        final long start = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .stream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println(System.currentTimeMillis() - start);

        System.out.println("Parallell Stream (8 elements)");
        final long start2 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println(System.currentTimeMillis() - start2);


        System.out.println("Parallell Stream (9 elements)");
        final long start3 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println(System.currentTimeMillis() - start3);


        System.out.println("Parallell Stream (8 elements) with parallellism : 7");
        final long start4 = System.currentTimeMillis();
        // 0 : 코어개수 = 1
        // 1 : 코어개수 = 2
        // 3 : 코어개수 = 4
        // 7 : 코어개수 = 8
        System.setProperty("java.util.concurrent.ForkJoinPool.common.paraleelism", "7 ");
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println(System.currentTimeMillis() - start4);


        System.out.println("Parallell Stream (8 elements) with parallellism : 3");
        final long start5 = System.currentTimeMillis();
        // 0 : 코어개수 = 1
        // 1 : 코어개수 = 2
        // 3 : 코어개수 = 4
        // 7 : 코어개수 = 8
        System.out.println("Before :" + ForkJoinPool.getCommonPoolParallelism());
        //동작안함 패치 된듯
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20 ");
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","1");
        System.out.println("After : " + ForkJoinPool.getCommonPoolParallelism());
        //싱글코어만 사용 "0" 해도 ParallelStream 사용하면 실행순서 보장하지 않음
        //ORM 사용시 class안에 다른 class를 가질(LAZY) 인 경우 parallelStream사용안함
        //Thread safe하지 않아 DataBaseAccess시 원하지 않는 결과를 얻을수 있음(Eagle)로 사용
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));
        System.out.println(System.currentTimeMillis() - start5);
    }
}
