package java8Tube;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExamples5ParallelPerformancePractical {
    private static final String[] priceStrings = {"1.0", "100.99", "35.75", "21.30", "88.00"};
    private static final BigDecimal[] targetPrices = {new BigDecimal("30"), new BigDecimal("20"), new BigDecimal("31")};
    private static final Random random = new Random(123);
    private static final Random targetPriceRandom = new Random(111);


    private static final List<Product> products;

    static {
        final int length = 8_000_000;
        final Product[] list = new Product[length];
        //final List<Product> list =new ArrayList<>(); ->final List<Product> list =new ArrayList<>(length);로 용량증가를 제한 할 수 있음
        //ArrayList는 초기에 length가 10인 array를 내부적으로 생성해서 add메소드 사용시 용량 확인을 해서 다찰때마다 1.5배를 합ㄴ디ㅏ.
        // 즉, array.length = 100,000 인데, 이게 꽉차면 그 후에 element 1개만 더추가를 해도 ArrayList의 내부크기는 이렇게 변합니다.
        // 100,000 * 1.5 = 150,000 (즉 array length : 100,000 -> 150,000)
        // 쓸대없이 메모리크기가 증가할 수 있다.
        for (int i = 1; i <= length; i++) {
            list[i - 1] = new Product((long) i, "Product" + i, new BigDecimal(priceStrings[random.nextInt(5)]));
        }
        //unmodifiableList 수정하지 못하게함 
        // -> Arrays.asList(list)는 수정이 불가능한 UnmodifiableList가 아니고 size가 고정된 List라서 추가나
        // 삭제가 불가능한것 뿐입니다. 그래서  unmodifiableList로 대체
        // list 배열은 새로 생성하지 않고 그대로 사용하기 때문에 속도가 빠르다.
        /*
            public static <T> List<T> asList(T... a) {
                return new Arrays.ArrayList(a);
            }
           ArrayList(E[] array) {
                this.a = (Object[])Objects.requireNonNull(array);
           }
         */
        products = Collections.unmodifiableList(Arrays.asList(list));
    }
    private static BigDecimal imperativeSum(final List<Product> products, final Predicate<Product> predicate) {
        BigDecimal sum = BigDecimal.ZERO;
        for (final Product product : products) {
            if (predicate.test(product)) {
                sum = sum.add(product.getPrice());
            }
        }
        return sum;
    }

    private static void imperativeTest(final BigDecimal targetPrice, final boolean printResult) {
        /**
         * Benchmark 코드라서 결코 일반 앱 개발등에 쓰기 좋은 코딩 스타일로 작성된것이 아닙니다.
         * (Stream사용 이외의) 이런 코드 작성을 절대 권장하지 않습니다.
         */
        if (printResult) {
            System.out.println("============================================");
            System.out.println("\nImperative Sum\n--------------------------------------------");
        }
        final long start = System.currentTimeMillis();
        final BigDecimal result = imperativeSum(products, product -> product.getPrice().compareTo(targetPrice) >= 0);
        final long howLong = System.currentTimeMillis() - start;
        if (printResult) {
            System.out.println("Sum: " + result);
            System.out.println("It took " + howLong + " ms.");
            System.out.println("============================================");
        }
    }
    private static BigDecimal streamSum(final Stream<Product> stream, final Predicate<Product> predicate) {
        return stream.filter(predicate).map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private static void streamTest(final BigDecimal targetPrice, final boolean printResult) {
        /**
         * Benchmark 코드라서 결코 일반 앱 개발등에 쓰기 좋은 코딩 스타일로 작성된것이 아닙니다.
         * (Stream사용 이외의) 이런 코드 작성을 절대 권장하지 않습니다.
         */
        if (printResult) {
            System.out.println("============================================");
            System.out.println("\nStream Sum\n--------------------------------------------");
        }
        final long start = System.currentTimeMillis();
        final BigDecimal result = streamSum(products.stream(), product -> product.getPrice().compareTo(targetPrice) >= 0);
        final long howLong = System.currentTimeMillis() - start;
        if (printResult) {
            System.out.println("Sum: " + result);
            System.out.println("It took " + howLong + " ms.");
            System.out.println("============================================");
        }
    }

    private static void parallelStreamTest(final BigDecimal targetPrice, final boolean printResult) {
        /**
         * Benchmark 코드라서 결코 일반 앱 개발등에 쓰기 좋은 코딩 스타일로 작성된것이 아닙니다.
         * (Stream사용 이외의) 이런 코드 작성을 절대 권장하지 않습니다.
         */
        if (printResult) {
            System.out.println("============================================");
            System.out.println("\nParallel Stream Sum\n--------------------------------------------");
        }
        final long start = System.currentTimeMillis();
        final BigDecimal result = streamSum(products.parallelStream(), product -> product.getPrice().compareTo(targetPrice) >= 0);
        final long howLong = System.currentTimeMillis() - start;
        if (printResult) {
            System.out.println("Sum: " + result);
            System.out.println("It took " + howLong + " ms.");
            System.out.println("============================================");
        }
    }


    public static void main(String[] args) {
        //products (List)에 담겨있는 Product의 상태는 변할 수 있지만,
        //products List의 상태는 변하지 않습니다. products List는 UnmodifiableList로 변경
        //그래서 추가, 삭제, 교체를 시도할 경우 UnsupportedOperationException이 발생
/*        BigDecimal targetPrice = new BigDecimal("40");
        //처음 테스트는 의미없다 jvm설정하는 시간이 들어감
        imperativeTest(targetPrice, true);
        streamTest(targetPrice, true);
        parallelStreamTest(targetPrice, true);
        System.out.println("\nIgnore Test Above\n==================================\n");
        System.out.println("Start!");*/

/*        for(int i=0;i<5;i++){
            BigDecimal price = targetPrices[targetPriceRandom.nextInt(3)];
            imperativeTest(targetPrice, true);
            streamTest(targetPrice, true);
            parallelStreamTest(targetPrice, true);
        }*/
        test1();
        test2();
        test3();
        //ParallelStream 주의사항
        //항상 빠르지 않음
        //벤치마크 툴로 JMH
    }


    private static void test1() {

        final BigDecimal targetPrice = new BigDecimal("40");

        imperativeTest(targetPrice, false);
        streamTest(targetPrice, false);
        parallelStreamTest(targetPrice, false);

        System.out.println("\n\n================================================================\nTest1 Starts!");
        for (int i = 0; i < 5; i++) {
            BigDecimal price = targetPrices[targetPriceRandom.nextInt(3)];

            imperativeTest(price, true);
            streamTest(price, true);
            parallelStreamTest(price, true);
        }
    }

    private static void test2() {

        final BigDecimal targetPrice = new BigDecimal("40");

        parallelStreamTest(targetPrice, false);
        imperativeTest(targetPrice, false);
        streamTest(targetPrice, false);

        System.out.println("\n\n================================================================\nTest2 Starts!");
        for (int i = 0; i < 5; i++) {
            BigDecimal price = targetPrices[targetPriceRandom.nextInt(3)];

            parallelStreamTest(price, true);
            imperativeTest(price, true);
            streamTest(price, true);
        }
    }

    private static void test3() {

        final BigDecimal targetPrice = new BigDecimal("40");

        streamTest(targetPrice, false);
        parallelStreamTest(targetPrice, false);
        imperativeTest(targetPrice, false);

        System.out.println("\n\n================================================================\nTest3 Starts!");
        for (int i = 0; i < 5; i++) {
            BigDecimal price = targetPrices[targetPriceRandom.nextInt(3)];

            streamTest(price, true);
            parallelStreamTest(price, true);
            imperativeTest(price, true);
        }
    }
}


@Data
@AllArgsConstructor
class Product{
    private Long id;
    private String name;
    private BigDecimal price;
}