package java8.ch04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExamplesStep2 {
    public static void main(String[] args) {
        Predicate<Integer> isPositive = i -> i > 0;

        System.out.println(isPositive.test(1));
        System.out.println(isPositive.test(0));
        System.out.println(isPositive.test(-1));

        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        Predicate<Integer> lessthan3 = i -> i < 3;
        System.out.println("positive integers : " + filter(numbers, isPositive));
        System.out.println("less than3 integers : " + filter(numbers, lessthan3));
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> filter){
        List<T> result = new ArrayList<>();
        for(T t : list){
            if(filter.test(t)){
                result.add(t);
            }
        }
        return result;
    }
}
