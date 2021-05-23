package java8.ch04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExamplesStep1 {
    public static void main(String[] args) {
        Predicate<Integer> isPositive = i -> i > 0;

        System.out.println(isPositive.test(1));
        System.out.println(isPositive.test(0));
        System.out.println(isPositive.test(-1));

        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);
        List<Integer> positiveNumbers = new ArrayList<>();
        for (Integer num : numbers) {
            if (isPositive.test(num))
                positiveNumbers.add(num);
        }
        System.out.println("positive integers : " + positiveNumbers);

        Predicate<Integer> lessthan3 = i -> i < 3;
        List<Integer> numberLessThan3 = new ArrayList<>();
        for (Integer num : numbers) {
            if (lessthan3.test(num))
                numberLessThan3.add(num);
        }
        System.out.println("less than3 integers : " + numberLessThan3);
    }
}
