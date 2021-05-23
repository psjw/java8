package java8.ch01;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class LambdaExpressionRaceStep3 {
    public static void main(String[] args) {
        System.out.println("\nLambdaExample.parallelProgramming");
        final long start = System.currentTimeMillis();
        /*
         * peek에 넘기는 function은 3초가 걸리는
         * 연산을 시뮬레이션한 것입니다.
         *
         * 코어 4개짜리 CPU에서 3초정도 걸립니다.
         * 저장된 숫자를 하나 더 늘리면 (1, 2, 3, 4, 5),
         * 처리하는데 8초정도 걸립니다.
         *
         * 저장된 숫자의 갯수를 CPU코어 만큼 늘려서 테스트 해보시고,
         * 코어보다 하나 많게 해서 테스트 해보세요.
         * 예) 코어가 8개면
         * (1, 2, 3, 4, 5, 6, 7, 8) => 3초정도 걸립니다.
         * (1, 2, 3, 4, 5, 6, 7, 8, 9) => 6초정도 걸립니다.
         */
        final int total = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .parallelStream()
                .peek(i -> {
                    // 처리 시간 오래 걸리는 함수 시뮬레이션
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                .reduce(0, (i1, i2) -> i1 + i2);
        System.out.println("It took " + ((System.currentTimeMillis() - start) / 1000) + " seconds.");
        System.out.println("total: " + total);
    }
}
