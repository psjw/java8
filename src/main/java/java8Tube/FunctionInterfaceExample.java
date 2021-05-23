package java8Tube;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

//인터페이스 안에 abatract 메소드 하나만 존재 -> Functional Interface
public class FunctionInterfaceExample {

    private void run4FunctionalInterfaces() {

        // T -> R 일반적 Mapper에 해당하는 Function
        // T -> T  identity function ( 타입을 받아서 그냥 넘김 )
        // ex) public int toInt(String value){ return Integer.parseInt(value); } -->  100 <= toInt("100")
        // identity ex1) public String f(String value){ return "value is "+value; } --> identity function은 아니다.
        // identity ex2) public String identity(String value){ return value; } --> identity function

        /*
        //return : String, paramenter : Integer
        Function<String, Integer> toInt = new Function<String, Integer>() {
            @Override
            public Integer apply(String value) {
                return Integer.parseInt(value);
            }
        };
         */
        //return : Integer, paramenter : String
        final Function<String, Integer> toInt = value -> Integer.parseInt(value); //파라미터가 하나면 () 없어도됨

        final Integer number = toInt.apply("100");
        System.out.println(number);

        //Identity Function
//        final Function<Integer, Integer> identity = Function.identity();
        final Function<Integer, Integer> identity = t -> t;
        System.out.println(identity.apply(999));

        //Consumer
        /*
        final Consumer<String> print = new Consumer<String>() {
            @Override
            public void accept(String value) {
                System.out.println(value);
            }
        };
         */
        final Consumer<String> print = value -> System.out.println(value);
        final Consumer<String> greetings = value -> System.out.println("Hello " + value);
//        final Function<String,Void> print2 = value -> System.out.println(value); //리턴값이 없어서 에러가 남
        print.accept("Hello");
        greetings.accept("World");
        //Predicate<>
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
        //중복코드가 너무 많다.

        System.out.println("positive integers : " + filter(numbers, isPositive));
        System.out.println("less than3 integers : " + filter(numbers, lessthan3));
        System.out.println("----------------------------------------------------------------------------");
        //Supplier
        final Supplier<String> helloSupplier = () -> "Hello ";
        System.out.println(helloSupplier.get() + " world");
        ;
//        printIfValidIndex(0, "psjw");
//        printIfValidIndex(1, "psjw");
//        printIfValidIndex(-1, "psjw");

        long start = System.currentTimeMillis();
        //9초
        printIfValidIndex(0, getVeryExpensiveValue());
        printIfValidIndex(-1, getVeryExpensiveValue()); // value가 필요없음 else조건 이므로
        printIfValidIndex(-2, getVeryExpensiveValue()); // value가 필요없음 else조건 이므로
        System.out.println("It took " + ((System.currentTimeMillis() - start) / 1000));
        System.out.println("----------------------------------------------------------------------------");
        long start1 = System.currentTimeMillis();
        //3초 조건이 맞아야 getVeryExpensiveValue()가 호출된다.
        Supplier<String> supplier = () -> getVeryExpensiveValue();
        printIfValidIndex(0, supplier);
        printIfValidIndex(-1, supplier); // value가 필요없음 else조건 이므로
        printIfValidIndex(-2, supplier); // value가 필요없음 else조건 이므로
        System.out.println("It took " + ((System.currentTimeMillis() - start1) / 1000));

        //@FunctionalInterface //Anootaion이 Functional Interface를 보장
        // 만약 abstract method 추가시 Compile Error 발생
        // 애초에 람다타입에 해당하는 Functional Interface 자체도 결국 해당 오브젝트가 생성돼야 하는데 
        // 메소드 바디가 없는 메소드를 남겨 놓고는 오브젝트 생성을 못시키기 때문에
        // 람다를 쓰려면 꼭 Single Abstract Method(SAM)여야 합니다.
        // 언어의 규칙 이다.
        //interface Functio<T,R> {R apply(T t);}
        //void doSomething (Function<Integer,String f)
        //doSomething(i -> String.valueOf(i) );
    }

    private static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Let's just say it has very expensive calculation here!
        return "psjw";
    }

    private static void printIfValidIndex(int num, String value) {
        if (num >= 0) {
            System.out.println("The value is " + value + ".");
        } else {
            System.out.println("Invalid");
        }
    }

    private static void printIfValidIndex(int num, Supplier<String> valueSupplier) {
        if (num >= 0) {
            System.out.println("The value is " + valueSupplier.get() + ".");
        } else {
            System.out.println("Invalid");
        }
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for (T input : list) {
            if (filter.test(input)) {
                result.add(input);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        println(1, 2, 3, (t1, t2, t3) -> String.valueOf(t1 + t2 + t3));
        println("Area is ", 12, 20, (message, length, width) -> message + (length * width));
        println(1L, "psjw", "test@email.com"
                , (id, name, email) -> "User info : ID = " + id + ", name = " + name + ", email = " + email);
        System.out.println("----------------------------------------------------------------------------");
        Function3<Integer, Integer, Integer, String> f3 = (i1, i2, i3) -> String.valueOf(1 + 2 + 3);

        //기존에는 generic type으로 인해 타입을 추론하여 적용
        //만약 명확한 경우에 사용
        BigDeciamlToCurrency bigDeciamlToCurrency = bd -> "$" + bd.toString();
        System.out.println(bigDeciamlToCurrency.toCurrency(new BigDecimal("120.00")));
        //Functional Intefce 제약사항
        // 제너릭 메소드면 Functional Interface 사용불가
        final InvalidFunctionalInterface anonoymousClass = new InvalidFunctionalInterface() {
            @Override
            public <T> String mkString(T value) {
                return value.toString();
            }
        };
        //Target method is generic //람다식 사용불가
        //타입 추론이 불가함
//        final InvalidFunctionalInterface invalidClass = value -> value.toString(); // 에러발생
        System.out.println(anonoymousClass.mkString("anonymous class : "+123));
    }

    private static <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3, Function3<T1, T2, T3, String> function) {
        System.out.println(function.apply(t1, t2, t3));
    }
}

// parmeter T1~T3 , return R
@FunctionalInterface
interface Function3<T1, T2, T3, R> {
    R apply(T1 t1, T2 t2, T3 t3);
//    void print(int i);  //abstract method 2개 되면 오류
}

@FunctionalInterface
interface BigDeciamlToCurrency{
    String toCurrency(BigDecimal value);
}

@FunctionalInterface
interface  InvalidFunctionalInterface{
    <T> String mkString(T value);
}