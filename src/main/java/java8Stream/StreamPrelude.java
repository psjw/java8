package java8Stream;


import java.util.*;
import java.util.function.Function;

public class StreamPrelude {
    public static void main(String[] args) {
        final int abs1 = Math.abs(1);
        final int abs2 = Math.abs(-1);

        //두개의 입력값이 같은 결과를 갖음
        System.out.println("abs1 = " + abs1);
        System.out.println("abs2 = " + abs2);
        System.out.println("abs1 == abs2 is " + (abs1 == abs2));
        final int minInt = Math.abs(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println("minInt = " + minInt); //음수가 나옴 -> 32bit 자바는 unsigned int 개념이 없고 모두 signed int : 인티저가  양수랑 음수 존재 : - 2^31 ~ 2^31 -1 (-1은 0때문에)
        System.out.println();
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("mapOld(numbers , i -> i *2");
        System.out.println(
                mapOld(numbers, i -> i * 2)
        );
        System.out.println("mapOld(numbers , null)");
        System.out.println(
                mapOld(numbers, null)
        );
        System.out.println();
        System.out.println("map(numbers , i -> i *2");
        System.out.println(
                map(numbers, i -> i * 2)
        );
//        System.out.println("map(numbers , null )");
//        System.out.println(
//                map(numbers, null)
//        );
        System.out.println("map(numbers , i -> i )");
        System.out.println(
                map(numbers, i -> i)
        );
        System.out.println("map(numbers , Function.identity() )");
        System.out.println(
                map(numbers, Function.identity())
        );

    }

    private static <T,R> List<R> map(final List<T> list, final  Function<T,R> mapper){
//        final Function<T, R> function;

        final List<R> result = new ArrayList<>();
        for(final T t : list){
            result.add(mapper.apply(t));
        }
        return result;
    }

    private static <T, R> List<R> mapOld(final List<T> list, final Function<T, R> mapper) {
        final List<R> result;
        if (mapper != null) {
            result = new ArrayList<>();
        } else {
            result = new ArrayList<R>((List<R>) list);
        }
        if (result.isEmpty()) {
            for (final T t : list) {
                result.add(mapper.apply(t));
            }
        }
        return result;
    }
}
