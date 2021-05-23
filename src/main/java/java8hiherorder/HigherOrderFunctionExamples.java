package java8hiherorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HigherOrderFunctionExamples {
    //function이  파라미터로 다른 function을 받음
    //Higher-Order-Function(HOF) 고계함수 or 고차함수
    //function을 실행 했을때 다른 function 리턴
    //Function<Function<Integer,String>,String>
    // = f -> f.apply(10)
    // f = x -> x.apply(10)
    // f.apply( i -> "#"+i)
    //Function<Integer,Function<Integer,Integer>>
    // f = i -> i2 -> i+i2 -> f.apply(1).apply(9) = i1+i2 = 1+9 = 10
    //i2 -> i+i2 (Function<Integer,Integer>)
    //Function<Integer,Function>
    public static void main(String[] args) {
        final Function<Function<Integer, String>, String> f = g -> g.apply(10);
        //f : Higher Order Function
        //g= i -> "#" + i
        System.out.println(f.apply(i -> "#" + i)); //#10

        final Function<Integer, Function<Integer, Integer>> f2 = i -> i2 -> i + i2;
        System.out.println(f2.apply(1).apply(9));//10

        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        final List<String> mappedList = map(list, i -> "#" + i);
        System.out.println(mappedList);
        System.out.println(list.stream().filter(i -> i > 2).map(i -> "#" + i).collect(Collectors.toList()));
        //    Function.identity()
        Function<Integer, Function<Integer, Function<Integer, Integer>>> f3 =
                i1 -> i2 -> i3 -> i1 + i2 + i3;
        Function<Integer, Function<Integer, Integer>> applied1 = f3.apply(1);
        Function<Integer, Integer> applied2 = applied1.apply(2);
        Integer applied3 = applied2.apply(3);
        System.out.println(applied3);

        System.out.println(
                "f3.apply(1).apply(2).apply(3) : " + f3.apply(1).apply(2).apply(3)
        );


        Function<Integer, Function<Integer, Integer>> plus10 = f3.apply(10);
        System.out.println(
                "plus10.apply(1).apply(1) : "+plus10.apply(1).apply(1)
        );

    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }
}
