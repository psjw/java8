package java8Stream;

import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExamples2 {
    public static void main(String[] args) {
        //Stream.of(1,2,3) //여러개 or 1개를 가진 스트림
        Stream.of(1, 2, 3, 4, 5).forEach(i -> System.out.print(i + " "));
        System.out.println();
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        final List<Integer> result = new ArrayList<>();
        Integer result = null;
        for (final Integer number : numbers) {
            if (number > 3 && number < 9) {
                final Integer newNumber = number * 2;
                if (newNumber > 10) {
//                    result.add(newNumber);
                    result = newNumber;
                    break;
                }
            }
        }
        System.out.println("Impreative Result = " + result);
        System.out.println("Functional Result : " +
                numbers.stream().filter(number -> {
                    System.out.println("number > 3 : " + number);
                    return number > 3;
                })
                        .filter(number -> {
                            System.out.println("number > 9 : " + number);
                            return number < 9;
                        })
                        .map(number -> {
                            System.out.println("number * 2 : " + number);
                            return number * 2;
                        })
                        .filter(number -> {
                            System.out.println("number > 10 : " + number);
                            return number > 10;
                        })
                        .findFirst()
        );

        System.out.println();

        System.out.println("Functional Result : " +
                numbers.stream().filter(number -> number > 3)
                        .filter(number -> number < 9)
                        .map(number -> number * 2)
                        .filter(number -> number > 10)
                        .findFirst()
        );
        System.out.println("============================");
        final List<Integer> greaterThan3 = filter(numbers, i -> i > 3);
        final List<Integer> lessThan3 = filter(numbers, i -> i < 9);
        final List<Integer> doubled = map(lessThan3, i -> i * 2);
        final List<Integer> greaterThan10 = filter(doubled, i -> i > 10);
        System.out.println(greaterThan10.get(0));
        System.out.println("============================");
        final List<Integer> myOwnMethodResult =
                filter(
                        map(
                                filter(
                                        filter(numbers
                                                , i -> i > 3)
                                        , i -> i < 9)
                                , i -> i * 2)
                        , i -> i > 10);
        System.out.println("My own method result : " + myOwnMethodResult);
        System.out.println("My own method result.get(0) : " + myOwnMethodResult.get(0));
        System.out.println("============================");
//        final AtomicInteger count = new AtomicInteger(1);
//        final List<Integer> greaterThan3 = filter(numbers, i -> {
//            System.out.println(count.getAndAdd(1) + " i > 3 : " + i);
//            return i > 3;
//        });
//        final List<Integer> lessThan3 = filter(numbers, i -> {
//            System.out.println(count.getAndAdd(1) + " i < 9 : " + i);
//            return i < 9;
//        });
//        final List<Integer> doubled = map(lessThan3, i -> {
//            System.out.println(count.getAndAdd(1) + " i * 2 : " + i);
//            return i * 2;
//        });
//        final List<Integer> greaterThan10 = filter(doubled, i -> {
//            System.out.println(count.getAndAdd(1) + " i > 10 : " + i);
//            return i > 10;
//        });
//        System.out.println("My own method result : "+greaterThan10);
//        System.out.println("My own method result : "+greaterThan10.get(0));

    }

    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        final List<T> result = new ArrayList<>();
        for (final T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }
}
