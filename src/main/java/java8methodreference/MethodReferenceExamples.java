package java8methodreference;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MethodReferenceExamples {
    //Functional Programming 언어가 아닌 일반적인 OOP에서,
    //function : 호출이 가능한 코드 ( e.g. sum(productPriceList))
    //method : 특정객체(object)에 종속된 호출 가능한 코드 (e.g. order.discountedTotal(10))
    //흔히 하는 function과 method의 구분을 간략하게 말한것입니다.
    //ClassName.methodName()
    //Something something =new Something();
    //something.methodName();
    //Interface가 method body를 가질수 있음(Default Method)
    /*
    @FunctionalInterface
    interface Function<T, R>{
        R appy(T t); // <= abstract method
     }
     Arrays.asList(1,2,3)
        .stream()
        .map(i->i*2) // <= lambda experession
        .collect(Collectors.toList());

        Arrays.asList(1,2,3)
        .stream()
        .map() //doubleIt 메소드를 여기에 파라미터로 넘기려면?
        .collect(Collectors.toList());
        public Function<Integer,Integer> getDoubleIt(){
            return // 여기서 doubleIt을 리턴하려면?
        }

        final List<Function<Integer,Integer>> fs =
        Arrays.aList( , )doubleIt과 plus10 메소드를 List에 저장할수 있을까요?
         
        public int plus10(int i){
            return i+10;
        }
        public int doubleIt(int i){
            return i*2;
        }
        --> Method Referece로 가능
     */

    public static void main(String[] args) {
        //FirstClass : function == method
        Arrays.asList(1, 2, 3, 4, 5) //Collection 자체에도 forEach메서드 있음
                .forEach(
//                        i -> System.out.println(i)
                        System.out::println
                );
        //forEach(Consumer<T>) -> 파라미터가 accept(T t) 이고 리턴이 void
        //println()  -> 파라미터가 Object 리턴이 void
        // --> 두개가 같은 유형
        //System.out::println //Method Reference로 넘김

        //First Class Function
        //1. return function ( function )
        //2. function function(parameter)
        //3.  F f = function
        //4. List<F> fs = Arrays.asList(function)
        //  list.add(function)


        System.out.println(
                Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
                        .stream()
                        .sorted(BigDecimalUtil::compare)
//            .sorted((bd1, bd2) -> bd1.compareTo(bd2))
                        .collect(Collectors.toList())
        );

        System.out.println(
                Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
                        .stream()
                        .sorted(BigDecimal::compareTo)
//            .sorted((bd1, bd2) -> bd1.compareTo(bd2))
                        .collect(Collectors.toList())
        );


        System.out.println("\nThe following three cases have the same result.");
        System.out.println("----------------------------------------------------");
        final List<String> abcdList = Arrays.asList("a", "b", "c", "d");
        final String targetString = "c";
        System.out.println("list: " + abcdList);
        System.out.println("targetString: \"c\"");
        System.out.println("\nanyMatch(targetString::equals)\n" +
                abcdList
                        .stream()
                        .anyMatch(targetString::equals)
        );
        System.out.println("\nanyMatch(\"c\"::equals)\n" +
                abcdList
                        .stream()
                        .anyMatch("c"::equals)
        );
        System.out.println("\nanyMatch(x -> x.equals(\"c\"))\n" +
                abcdList
                        .stream()
                        .anyMatch(x -> x.equals("c"))
        );
        System.out.println("\n=========================================");
        System.out.println("methodReference03();");
        System.out.println("-----------------------------------------");
        methodReference03();
    }

    private static void methodReference03() {
        /* First Class Function */
        /**
         * A function can be passed as a parameter to another function.
         */
//            Using Lambda Expression
        System.out.println(testFirstClassFunction1(3, i -> String.valueOf(i * 2)));
//          Using Method Reference
        System.out.println(testFirstClassFunction1(3, MethodReferenceExamples::doubleThenToString));
        /**
         * A function can be stored in the data structure.
         */
        //            Using Lambda Expression
        final Function<Integer, String> fl = getDoubleThenToStringUsingLambdaExpression();
        final String resultFromFl = fl.apply(3);
        System.out.println(resultFromFl);
        //Using Method Reference
        final Function<Integer, String> fmr = getDoubleThenToStringUsingMethodReference();
        final String resultFromFmr = fmr.apply(3);
        System.out.println(resultFromFmr);
        System.out.println("\n-----------------------------------------");
        /**
         * A function can be stored in the data structure.
         */
        //Using Lambda Expression
        final List<Function<Integer, String>> fsl = Arrays.asList(i -> String.valueOf(i * 2));

        for (final Function<Integer, String> f : fsl) {
            final String result = f.apply(3);
            System.out.println(result);
        }
        //Using Method Reference
        final List<Function<Integer, String>> fmsr = Arrays.asList(MethodReferenceExamples::doubleThenToString);
        for (final Function<Integer, String> f : fmsr) {
            final String result = f.apply(3);
            System.out.println(result);
        }
        System.out.println("\n-----------------------------------------");
        /**
         * A function can be assigned in the variable.
         */
        //Using Lambda Expression
        final Function<Integer, String> fl2 = i -> String.valueOf(i * 2);
        final String resultFl2 = fl2.apply(5);
        System.out.println(resultFl2);

        //Using Method Reference
        final Function<Integer, String> fmr2 = MethodReferenceExamples::doubleThenToString;
        final String resultFmr2 = fmr2.apply(5);
        System.out.println(resultFmr2);
        System.out.println("\n-----------------------------------------");
        /**
         * Both Lambda Expression and Method Reference
         */
        List<Function<Integer, String>> fsBoth = Arrays.asList(i -> String.valueOf(i * 2)
                , MethodReferenceExamples::doubleThenToString
                ,MethodReferenceExamples::addHashPrefix);
        for(final Function<Integer,String> f : fsBoth){
            final String result = f.apply(7);
            System.out.println(result);
        }

    }


    private static String doubleThenToString(int i) {
        return String.valueOf(i * 2);
    }

    private static String addHashPrefix(int number){
        return "#" + number;
    }

    private static String testFirstClassFunction1(int n, Function<Integer, String> f) {
        return "The result is " + f.apply(n);
    }

    private static Function<Integer, String> getDoubleThenToStringUsingLambdaExpression() {
        return i -> String.valueOf(i * 2);
    }

    private static Function<Integer, String> getDoubleThenToStringUsingMethodReference() {
        return MethodReferenceExamples::doubleThenToString;
    }

}

class BigDecimalUtil {
    public static int compare(BigDecimal bd1, BigDecimal bd2) {
        return bd1.compareTo(bd2);
    }
}


